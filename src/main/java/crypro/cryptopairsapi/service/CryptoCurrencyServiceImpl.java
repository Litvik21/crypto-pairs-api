package crypro.cryptopairsapi.service;

import java.util.Comparator;
import java.util.List;
import crypro.cryptopairsapi.dto.ExternalResponseDto;
import crypro.cryptopairsapi.dto.mapper.CryptoCurrencyMapper;
import crypro.cryptopairsapi.model.CryptoCurrency;
import crypro.cryptopairsapi.repository.CryptoCurrencyRepository;
import crypro.cryptopairsapi.service.util.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    private final CryptoCurrencyRepository repository;
    private final CryptoCurrencyMapper mapper;
    @Value("${crypro.cryptopairsapi.key}")
    private String currencyLink;
    private final HttpClient httpClient;

    public CryptoCurrencyServiceImpl(CryptoCurrencyRepository repository,
                                     CryptoCurrencyMapper mapper,
                                     HttpClient httpClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.httpClient = httpClient;
    }

    @Scheduled(cron = "10 * * * *")
    @Override
    public void syncExternalCharacters() {
        List<String> cryptoCurrencies = List.of("BTC/USD", "ETH/USD", "XRP/USD");
        for (String cryptoCurrency : cryptoCurrencies) {
            CryptoCurrency currency = mapper.toModel(httpClient
                    .get(currencyLink + cryptoCurrency, ExternalResponseDto.class));
            save(currency);
        }
    }

    @Override
    public CryptoCurrency getMinPrice(String currencyName) {
        List<CryptoCurrency> currencies = repository.findByFirstSymbol(currencyName);
        return currencies.stream().min(Comparator.comparing(CryptoCurrency::getPrice)).orElseThrow(() ->
                new RuntimeException("Min price by this currency name: " + currencyName + " - NOT FOUND"));
    }

    @Override
    public CryptoCurrency getMaxPrice(String currencyName) {
        List<CryptoCurrency> currencies = repository.findByFirstSymbol(currencyName);
        return currencies.stream().max(Comparator.comparing(CryptoCurrency::getPrice)).orElseThrow(() ->
                new RuntimeException("Max price by this currency name: " + currencyName + " - NOT FOUND"));
    }

    @Override
    public void save(CryptoCurrency currency) {
        repository.save(currency);
    }

    @Override
    public List<CryptoCurrency> findByFirstSymbol(PageRequest pageRequest, String currencyName) {
        return repository.findByFirstSymbol(pageRequest, currencyName);
    }
}