package crypro.cryptopairsapi.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExternalResponseDto {
    private BigDecimal lprice;
    private String curr1;
    private String curr2;
}
