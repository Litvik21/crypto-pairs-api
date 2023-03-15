package crypro.cryptopairsapi.repository;

import crypro.cryptopairsapi.model.CryptoCurrency;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, Long> {

    List<CryptoCurrency> findByFirstSymbol(PageRequest pageRequest, String currencyName);

    Optional<CryptoCurrency> findFirstByFirstSymbolOrderByPriceAsc(String firstSymbol);

    Optional<CryptoCurrency> findFirstByFirstSymbolOrderByPriceDesc(String firstSymbol);
}
