package crypro.cryptopairsapi.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@Document
public class CryptoCurrency {
    @Transient
    public static final String SEQUENCE_NAME = "currency_sequence";
    @Id
    private long id;
    private String pair;
    private String firstSymbol;
    private String secondSymbol;
    private BigDecimal price;

    @Override
    public String toString() {
        return "CryptoCurrency{"
                + "id=" + id
                + ", pair='" + pair + '\''
                + ", firstSymbol='" + firstSymbol + '\''
                + ", secondSymbol='" + secondSymbol + '\''
                + ", price=" + price
                + '}';
    }
}
