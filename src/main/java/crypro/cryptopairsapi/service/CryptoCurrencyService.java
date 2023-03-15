package crypro.cryptopairsapi.service;

import crypro.cryptopairsapi.model.CryptoCurrency;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface CryptoCurrencyService {
    void syncExternalCharacters();

    CryptoCurrency getMinPrice(String currencyName);

    CryptoCurrency getMaxPrice(String currencyName);

    void save(CryptoCurrency currency);

    List<CryptoCurrency> findByFirstSymbol(PageRequest pageRequest, String currencyName);
}
