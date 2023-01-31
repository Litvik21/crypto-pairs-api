package crypro.cryptopairsapi.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaxCurrencyPriceResponseDto {
    private String pair;
    private BigDecimal maxPrice;
}
