package crypro.cryptopairsapi.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MinCurrencyPriceResponseDto {
    private String pair;
    private BigDecimal minPrice;
}
