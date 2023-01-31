package crypro.cryptopairsapi.service.util;

import crypro.cryptopairsapi.model.CryptoCurrency;
import crypro.cryptopairsapi.service.SequenceGeneratorService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class CryptoCurrencyListenerUtil extends AbstractMongoEventListener<CryptoCurrency> {
    private final SequenceGeneratorService sequenceGenerator;

    public CryptoCurrencyListenerUtil(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<CryptoCurrency> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(CryptoCurrency.SEQUENCE_NAME));
        }
    }
}
