package crypro.cryptopairsapi.repository;


import java.util.List;
import crypro.cryptopairsapi.model.CryptoCurrency;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, Long> {
    List<CryptoCurrency> findByFirstSymbol(String currencyName);

    List<CryptoCurrency> findByFirstSymbol(PageRequest pageRequest, String currencyName);
}
