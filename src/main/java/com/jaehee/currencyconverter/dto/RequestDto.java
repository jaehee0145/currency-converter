package com.jaehee.currencyconverter.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class RequestDto {
    //TODO 송금국가가 미국으로 고정인데 외부 Api에서 다른 국가 환율을 제공할때
    private String baseCurrency;

    @NotEmpty
    private String quoteCurrency;

    @Min(0)
    @Max(10)
    private int amount;

}
