package crypro.cryptopairsapi.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import crypro.cryptopairsapi.dto.CryptoCurrencyResponseDto;
import crypro.cryptopairsapi.dto.MaxCurrencyPriceResponseDto;
import crypro.cryptopairsapi.dto.MinCurrencyPriceResponseDto;
import crypro.cryptopairsapi.dto.mapper.CryptoCurrencyMapper;
import crypro.cryptopairsapi.model.CryptoCurrency;
import crypro.cryptopairsapi.service.CryptoCurrencyService;
import crypro.cryptopairsapi.service.util.CsvFileGenerator;
import crypro.cryptopairsapi.service.util.SortUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cryptocurrencies")
public class CryptoCurrencyController {
    private final CryptoCurrencyService currencyService;
    private final CryptoCurrencyMapper mapper;

    private final CsvFileGenerator csvFileGenerator;

    public CryptoCurrencyController(CryptoCurrencyService currencyService,
                                    CryptoCurrencyMapper mapper,
                                    CsvFileGenerator csvFileGenerator) {
        this.currencyService = currencyService;
        this.mapper = mapper;
        this.csvFileGenerator = csvFileGenerator;
    }

    @GetMapping("/minprice")
    public MinCurrencyPriceResponseDto getMinPrice(@RequestParam("name") String currencyName) {
        CryptoCurrency currency = currencyService.getMinPrice(currencyName);
        return new MinCurrencyPriceResponseDto(currency.getPair(), currency.getPrice());
    }

    @GetMapping("/maxprice")
    public MaxCurrencyPriceResponseDto getMaxPrice(@RequestParam("name") String currencyName) {
        CryptoCurrency currency = currencyService.getMaxPrice(currencyName);
        return new MaxCurrencyPriceResponseDto(currency.getPair(), currency.getPrice());
    }

    @GetMapping
    public List<CryptoCurrencyResponseDto> getAll(@RequestParam("name") String currencyName,
                                                  @RequestParam(defaultValue = "10")
                                                  Integer size,
                                                  @RequestParam(defaultValue = "0")
                                                  Integer page) {
        Sort sort = Sort.by(SortUtil.sort("price"));
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return currencyService.findByFirstSymbol(pageRequest, currencyName).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/csv")
    public void generateCsvRepo(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"currencies.csv\"");
        try {
            csvFileGenerator.writeDataToCsv(response.getWriter());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/demo")
    public void demo(){
        currencyService.syncExternalCharacters();
    }
}
