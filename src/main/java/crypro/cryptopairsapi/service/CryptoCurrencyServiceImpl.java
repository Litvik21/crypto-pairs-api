package crypro.cryptopairsapi.service;

import crypro.cryptopairsapi.dto.external.ApiResponseDto;
import crypro.cryptopairsapi.dto.mapper.LastPricePairMapper;
import crypro.cryptopairsapi.model.LastPricePair;
import crypro.cryptopairsapi.repository.LastPricePairRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class LastPricePairServiceImpl implements LastPricePairService {
    private final LastPricePairRepository repository;
    private final LastPricePairMapper mapper;
    @Value("https://cex.io/api/last_price/")
    private String CurrencyLink;
    private final HttpClient httpClient;

    public LastPricePairServiceImpl(LastPricePairRepository repository, LastPricePairMapper mapper, HttpClient httpClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.httpClient = httpClient;
    }

    @Override
    public List<LastPricePair> getAll() {
        return repository.findAll();
    }

    @Scheduled(cron = "10 * * * *")
    private void syncExternalPairs() {
        List<String> linksEnd = List.of("BTC/USD", "ETH/USD", "XRP/USD");
        for (String linkEnd : linksEnd) {
            ApiResponseDto dto = httpClient
                    .get(CurrencyLink + linkEnd, ApiResponseDto.class);
            repository.save(mapper.toModel(dto));
        }
    }
}
