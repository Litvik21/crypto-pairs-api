package crypro.cryptopairsapi.dto.mapper;

import crypro.cryptopairsapi.dto.external.ApiResponseDto;
import crypro.cryptopairsapi.model.LastPricePair;
import org.springframework.stereotype.Component;

@Component
public class LastPricePairMapper {
    public LastPricePair toModel(ApiResponseDto dto) {
        LastPricePair pair = new LastPricePair();
        pair.setPair(dto.getCurr1() + "/" + dto.getCurr2());
        pair.setFirstSymbol(dto.getCurr1());
        pair.setSecondSymbol(dto.getCurr2());
        pair.setLastPrice(dto.getLprice());
        return pair;
    }
}
