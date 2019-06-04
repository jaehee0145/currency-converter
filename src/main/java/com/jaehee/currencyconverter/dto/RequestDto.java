package com.jaehee.currencyconverter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {
    private String baseCurrency;
    private String quoteCurrency;
    private double amount;
}
