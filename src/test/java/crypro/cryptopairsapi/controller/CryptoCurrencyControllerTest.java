package crypro.cryptopairsapi.controller;

import java.math.BigDecimal;
import crypro.cryptopairsapi.model.CryptoCurrency;
import crypro.cryptopairsapi.service.CryptoCurrencyService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CryptoCurrencyControllerTest {
    @MockBean
    private CryptoCurrencyService service;
    @Autowired
    private MockMvc mockMvc;
    private CryptoCurrency currency;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        currency = new CryptoCurrency();
        currency.setPrice(BigDecimal.valueOf(123.45));
        currency.setPair("BTC/USD");
    }

    @Test
    void shouldReturnMinPriceOfCurrency() {
        Mockito.when(service.getMinPrice("BTC"))
                .thenReturn(currency);

        RestAssuredMockMvc.given()
                .when()
                .get("/cryptocurrencies/minprice?name=BTC")
                .then()
                .statusCode(200)
                .body("pair", Matchers.equalTo("BTC/USD"))
                .body("minPrice", Matchers.equalTo(123.45F));
    }

    @Test
    void shouldReturnMaxPriceOfCurrency() {
        Mockito.when(service.getMaxPrice("BTC"))
                .thenReturn(currency);

        RestAssuredMockMvc.given()
                .when()
                .get("/cryptocurrencies/maxprice?name=BTC")
                .then()
                .statusCode(200)
                .body("pair", Matchers.equalTo("BTC/USD"))
                .body("maxPrice", Matchers.equalTo(123.45F));
    }
}