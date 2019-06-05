package com.jaehee.currencyconverter.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyDto {
    //TODO dto가 적절한 이름인지 확인
    private String source;
    private Map<String, Double> quotes;
}
