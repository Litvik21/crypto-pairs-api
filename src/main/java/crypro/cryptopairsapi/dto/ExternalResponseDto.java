package crypro.cryptopairsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExternalResponseDto {
    @JsonProperty(value = "lprice")
    private BigDecimal lastPrice;
    @JsonProperty(value = "curr1")
    private String firstCurrency;
    @JsonProperty(value = "curr2")
    private String secondCurrency;
}
