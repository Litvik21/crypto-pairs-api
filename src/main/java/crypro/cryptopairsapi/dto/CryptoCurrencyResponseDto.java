package crypro.cryptopairsapi.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CryptoCurrencyResponseDto {
    private String pair;
    private String firstSymbol;
    private String secondSymbol;
    private BigDecimal price;
}