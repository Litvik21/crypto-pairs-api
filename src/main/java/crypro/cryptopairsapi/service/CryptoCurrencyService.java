package crypro.cryptopairsapi.service;

import crypro.cryptopairsapi.model.LastPricePair;

import java.util.List;

public interface LastPricePairService {
    List<LastPricePair> getAll();
}
