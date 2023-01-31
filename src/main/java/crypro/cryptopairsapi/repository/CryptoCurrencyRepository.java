package crypro.cryptopairsapi.repository;


import crypro.cryptopairsapi.model.LastPricePair;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LastPricePairRepository extends MongoRepository<LastPricePair, String> {
}
