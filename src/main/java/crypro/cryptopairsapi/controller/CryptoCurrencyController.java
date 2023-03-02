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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Get min price of chosen cryptocurrency")
    public MinCurrencyPriceResponseDto getMinPrice(
            @RequestParam("name")
            @ApiParam(value = "enter currency name")
            String currencyName) {

        CryptoCurrency currency = currencyService.getMinPrice(currencyName);
        return new MinCurrencyPriceResponseDto(currency.getPair(), currency.getPrice());
    }

    @GetMapping("/maxprice")
    @ApiOperation(value = "Get max price of chosen cryptocurrency")
    public MaxCurrencyPriceResponseDto getMaxPrice(
            @RequestParam("name")
            @ApiParam(value = "enter currency name")
            String currencyName) {

        CryptoCurrency currency = currencyService.getMaxPrice(currencyName);
        return new MaxCurrencyPriceResponseDto(currency.getPair(), currency.getPrice());
    }

    @GetMapping
    @ApiOperation(value = "Get list of chosen cryptocurrency")
    public List<CryptoCurrencyResponseDto> getAll(
            @RequestParam("name")
            @ApiParam(value = "enter currency name")
            String currencyName,
            @RequestParam(defaultValue = "10")
            @ApiParam(value = "enter size of elements on page")
            Integer size,
            @RequestParam(defaultValue = "0")
            @ApiParam(value = "enter number of page")
            Integer page) {

        Sort sort = Sort.by(SortUtil.sort("price"));
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return currencyService.findByFirstSymbol(pageRequest, currencyName).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/csv")
    @ApiOperation(value = "Get report of cryptocurrencies in csv file")
    public void generateCsvRepo(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"currencies.csv\"");
        try {
            csvFileGenerator.writeDataToCsv(response.getWriter());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to csv. ", e);
        }
    }

    @GetMapping("/demo")
    @ApiOperation(value = "Getting data of cryptocurrencies from api")
    public void demo(){
        currencyService.syncExternalCharacters();
    }
}