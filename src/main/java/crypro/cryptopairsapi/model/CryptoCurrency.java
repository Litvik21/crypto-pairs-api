package crypro.cryptopairsapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Document
public class LastPricePair {
    @Id
    private String pair;
    private String firstSymbol;
    private String secondSymbol;
    private BigDecimal lastPrice;
}
