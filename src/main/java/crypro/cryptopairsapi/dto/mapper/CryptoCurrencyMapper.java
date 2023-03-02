package crypro.cryptopairsapi.dto.mapper;

import crypro.cryptopairsapi.dto.CryptoCurrencyResponseDto;
import crypro.cryptopairsapi.dto.ExternalResponseDto;
import crypro.cryptopairsapi.model.CryptoCurrency;
import org.springframework.stereotype.Component;

@Component
public class CryptoCurrencyMapper {
    public CryptoCurrency toModel(ExternalResponseDto dto) {
        CryptoCurrency currency = new CryptoCurrency();
        currency.setPair(dto.getFirstCurrency() + "/" + dto.getSecondCurrency());
        currency.setFirstSymbol(dto.getFirstCurrency());
        currency.setSecondSymbol(dto.getSecondCurrency());
        currency.setPrice(dto.getLastPrice());

        return currency;
    }

    public CryptoCurrencyResponseDto toDto(CryptoCurrency currency) {
        CryptoCurrencyResponseDto dto = new CryptoCurrencyResponseDto();
        dto.setPair(currency.getPair());
        dto.setFirstSymbol(currency.getFirstSymbol());
        dto.setSecondSymbol(currency.getSecondSymbol());
        dto.setPrice(currency.getPrice());

        return dto;
    }
}