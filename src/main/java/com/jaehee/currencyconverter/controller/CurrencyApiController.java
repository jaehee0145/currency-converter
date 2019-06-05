package com.jaehee.currencyconverter.controller;

import com.jaehee.currencyconverter.dto.RequestDto;
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

    @GetMapping("/test")
    public String test(){
        return "return";
    }

    @GetMapping("/exchange-rate")
    public ResponseEntity<Double> getExchangeRate(@ModelAttribute RequestDto requestDto) {
        Double exchangeRate = currencyService.getExchangeRate(requestDto.getQuoteCurrency());
        return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
    }

    @GetMapping("/remittance")
    public ResponseEntity<Double> getRemittance(@ModelAttribute RequestDto requestDto) {
        Double exchangeRate = currencyService.getExchangeRate(requestDto.getQuoteCurrency());
        Double remittance = currencyService.getRemittance(exchangeRate, requestDto.getAmount());
        return new ResponseEntity<>(remittance, HttpStatus.OK);
    }
}
