package crypro.cryptopairsapi.service;

import java.math.BigDecimal;
import java.util.Optional;
import crypro.cryptopairsapi.model.CryptoCurrency;
import crypro.cryptopairsapi.repository.CryptoCurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CryptoCurrencyServiceImplTest {
    @InjectMocks
    private CryptoCurrencyServiceImpl currencyService;
    @Mock
    private CryptoCurrencyRepository repository;
    private CryptoCurrency currencyMinPrice;
    private CryptoCurrency currencyMaxPrice;

    @BeforeEach
    void setUp() {
        currencyMinPrice = new CryptoCurrency();
        currencyMinPrice.setPair("BTC/USD");
        currencyMinPrice.setPrice(BigDecimal.valueOf(111.22));

        currencyMaxPrice = new CryptoCurrency();
        currencyMaxPrice.setPair("BTC/USD");
        currencyMaxPrice.setPrice(BigDecimal.valueOf(432.12));
    }

    @Test
    void shouldReturnCurrencyByMinPrice() {
        Mockito.when(repository.findFirstByFirstSymbolOrderByPriceAsc("BTC"))
                .thenReturn(Optional.ofNullable(currencyMinPrice));
        CryptoCurrency actual = currencyService.getMinPrice("BTC");
        CryptoCurrency expected = new CryptoCurrency();
        expected.setPair("BTC/USD");
        expected.setPrice(BigDecimal.valueOf(111.22));
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getPair(), actual.getPair());
    }

    @Test
    void shouldReturnCurrencyByMaxPrice() {
        Mockito.when(repository.findFirstByFirstSymbolOrderByPriceDesc("BTC"))
                .thenReturn(Optional.ofNullable(currencyMaxPrice));
        CryptoCurrency actual = currencyService.getMaxPrice("BTC");
        CryptoCurrency expected = new CryptoCurrency();
        expected.setPair("BTC/USD");
        expected.setPrice(BigDecimal.valueOf(432.12));
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getPair(), actual.getPair());
    }
}
