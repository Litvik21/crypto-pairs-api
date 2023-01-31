package crypro.cryptopairsapi.service;

import crypro.cryptopairsapi.model.CryptoCurrency;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class CryptoCurrencyListener extends AbstractMongoEventListener<CryptoCurrency> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<CryptoCurrency> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(CryptoCurrency.SEQUENCE_NAME));
        }
    }
}
