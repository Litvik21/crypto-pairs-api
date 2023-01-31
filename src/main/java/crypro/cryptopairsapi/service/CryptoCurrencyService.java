package crypro.cryptopairsapi.service;

import java.util.List;
import crypro.cryptopairsapi.model.CryptoCurrency;
import org.springframework.data.domain.PageRequest;

public interface CryptoCurrencyService {
    void syncExternalCharacters();
    List<CryptoCurrency> getAll(PageRequest pageRequest);

    CryptoCurrency getMinPrice(String currencyName);

    CryptoCurrency getMaxPrice(String currencyName);

    void save(CryptoCurrency currency);

    List<CryptoCurrency> findByFirstSymbol(PageRequest pageRequest, String currencyName);

}
