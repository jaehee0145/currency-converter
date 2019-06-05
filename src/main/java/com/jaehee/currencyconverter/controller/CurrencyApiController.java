package com.jaehee.currencyconverter.controller;

import com.jaehee.currencyconverter.dto.RequestDto;
import com.jaehee.currencyconverter.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyApiController {
    private final CurrencyService currencyService;

    @GetMapping("/test")
    public String test(){
        return "return";
    }

    @GetMapping("/exchange-rate")
    public Double getExchangeRate(@ModelAttribute RequestDto requestDto) {
        Double exchangeRate = currencyService.getExchangeRate(requestDto.getQuoteCurrency());
        return exchangeRate;
    }

    @GetMapping("/remittance")
    public Double getRemittance(@ModelAttribute RequestDto requestDto) {
        Double exchangeRate = currencyService.getExchangeRate(requestDto.getQuoteCurrency());
        Double remittance = currencyService.getRemittance(exchangeRate, requestDto.getAmount());
        return remittance;
    }
}
