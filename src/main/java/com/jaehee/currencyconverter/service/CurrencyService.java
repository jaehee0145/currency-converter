package com.jaehee.currencyconverter.service;

import com.jaehee.currencyconverter.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyAPIService currencyAPIService;

    public Double currencyDto(String quoteCurrency){
        CurrencyDto currencyDto = currencyAPIService.getCurrencyDto();
        return currencyDto.getQuotes().get("USD"+quoteCurrency);
    }

    //환율, 금액으로 송금액 계산하기
    public Double getRemittance(Double exchangeRate, Double amount){
        return exchangeRate*amount;
    };
}
