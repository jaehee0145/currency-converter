package com.jaehee.currencyconverter.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class RequestDto {

    @NotEmpty
    private String quoteCurrency;

    @Min(0)
    @Max(10)
    private int amount;

}
