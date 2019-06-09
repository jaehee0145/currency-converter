package com.jaehee.currencyconverter.controller;

import com.jaehee.currencyconverter.dto.RequestDto;
import com.jaehee.currencyconverter.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyApiController {
    private final CurrencyService currencyService;


    @GetMapping("/exchange-rate")
    public ResponseEntity getExchangeRate(@Valid @ModelAttribute RequestDto requestDto, Errors errors) {
        Double exchangeRate = currencyService.getExchangeRate(requestDto.getQuoteCurrency());
       //TODO 테스트코드 필요
//        if(requestDto==null){
//            ResponseDto responseDto = new ResponseDto();
//            responseDto.setMessage("Error");
//            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
//        }
        return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
    }

    @GetMapping("/remittance")
    public ResponseEntity getRemittance(@ModelAttribute RequestDto requestDto, Errors errors) {

        Double exchangeRate = currencyService.getExchangeRate(requestDto.getQuoteCurrency());
        Double remittance = currencyService.getRemittance(exchangeRate, requestDto.getAmount());
        return new ResponseEntity<> (remittance, HttpStatus.OK);
    }
}
