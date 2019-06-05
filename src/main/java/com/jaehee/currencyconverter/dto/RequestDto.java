package com.jaehee.currencyconverter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {
    //TODO 송금국가가 미국으로 고정인데 외부 Api에서 다른 국가 환율을 제공할때
    private String baseCurrency;
    private String quoteCurrency;
    private double amount;
}
