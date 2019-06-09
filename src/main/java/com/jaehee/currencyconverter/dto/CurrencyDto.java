package com.jaehee.currencyconverter.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyDto {
    private boolean success;
    private String source;
    private Map<String, Double> quotes;
    private Map<String, String> error;
}
