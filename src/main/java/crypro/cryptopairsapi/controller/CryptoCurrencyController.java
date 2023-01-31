package crypro.cryptopairsapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cryptocurrencies")
public class CryptoCurrenciesController {
    @GetMapping("/minprice")
    public BigDecimal getMinPrice(@RequestParam String currencyName) {

    }
}
