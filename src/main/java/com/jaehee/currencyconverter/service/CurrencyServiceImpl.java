package com.jaehee.currencyconverter.service;

import com.jaehee.currencyconverter.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService{
    private final RestTemplate restTemplate;

    @Override
    public CurrencyDto getCurrencyDto() {
        String url = "http://apilayer.net/api/live?access_key=2ce8264e0ef3cfe017c7f254d68b65ad";
        CurrencyDto currencyDto = restTemplate.getForObject(url, CurrencyDto.class);
        return currencyDto;
    }
}
