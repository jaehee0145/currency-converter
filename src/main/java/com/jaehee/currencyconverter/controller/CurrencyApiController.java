package com.jaehee.currencyconverter.controller;

import com.jaehee.currencyconverter.dto.CurrencyDto;
import com.jaehee.currencyconverter.dto.RequestDto;
import com.jaehee.currencyconverter.service.CurrencyAPIService;
import com.jaehee.currencyconverter.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyApiController {
    private final CurrencyService currencyService;

    @GetMapping("/currency-info")
    public Double getCurrencyInfo(@ModelAttribute RequestDto requestDto){
        System.out.println("controller"+requestDto.getQuoteCurrency());
    Double exchangeRate = currencyService.currencyDto(requestDto.getQuoteCurrency());
    return exchangeRate;
    }
}
