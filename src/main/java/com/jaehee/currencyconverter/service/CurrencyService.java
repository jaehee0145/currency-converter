package com.jaehee.currencyconverter.service;

import com.jaehee.currencyconverter.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyAPIService currencyAPIService;

    //수취 국가에 따라 환율 정보 가져오기
    public Double getExchangeRate(String quoteCurrency){
        CurrencyDto currencyDto = currencyAPIService.getCurrencyDto();
        Double exchangeRate = currencyDto.getQuotes().get("USD"+quoteCurrency);
        return exchangeRate;
    }

    //환율, 금액으로 송금액 계산하기
    public Double getRemittance(Double exchangeRate, Double amount){
        Double remittance = exchangeRate*amount;
        return remittance;
    };
}
