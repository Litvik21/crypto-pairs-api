package crypro.cryptopairsapi.service;

import java.math.BigDecimal;
import java.util.List;
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
    private List<CryptoCurrency> currencies;

    @BeforeEach
    void setUp() {
        CryptoCurrency currency1 = new CryptoCurrency();
        currency1.setPair("BTC/USD");
        currency1.setPrice(BigDecimal.valueOf(423.55));
        CryptoCurrency currency2 = new CryptoCurrency();
        currency2.setPair("BTC/USD");
        currency2.setPrice(BigDecimal.valueOf(123.45));
        CryptoCurrency currency3 = new CryptoCurrency();
        currency3.setPair("BTC/USD");
        currency3.setPrice(BigDecimal.valueOf(111.22));
        CryptoCurrency currency4 = new CryptoCurrency();
        currency4.setPair("BTC/USD");
        currency4.setPrice(BigDecimal.valueOf(321.45));
        CryptoCurrency currency5 = new CryptoCurrency();
        currency5.setPair("BTC/USD");
        currency5.setPrice(BigDecimal.valueOf(432.12));
        currencies = List.of(currency1, currency2, currency3, currency4, currency5);
    }

    @Test
    void shouldReturnCurrencyByMinPrice() {
        Mockito.when(repository.findByFirstSymbol("BTC"))
                .thenReturn(currencies);
        CryptoCurrency actual = currencyService.getMinPrice("BTC");
        CryptoCurrency expected = new CryptoCurrency();
        expected.setPair("BTC/USD");
        expected.setPrice(BigDecimal.valueOf(111.22));
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getPair(), actual.getPair());
    }

    @Test
    void shouldReturnCurrencyByMaxPrice() {
        Mockito.when(repository.findByFirstSymbol("BTC"))
                .thenReturn(currencies);
        CryptoCurrency actual = currencyService.getMaxPrice("BTC");
        CryptoCurrency expected = new CryptoCurrency();
        expected.setPair("BTC/USD");
        expected.setPrice(BigDecimal.valueOf(432.12));
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getPair(), actual.getPair());
    }
}