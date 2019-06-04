package com.jaehee.currencyconverter.service;

import com.jaehee.currencyconverter.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CurrencyAPIServiceImpl implements CurrencyAPIService {
    private final RestTemplate restTemplate;



    @Override
    public CurrencyDto getCurrencyDto() {
        String url = "http://apilayer.net/api/live?access_key=2ce8264e0ef3cfe017c7f254d68b65ad";
        CurrencyDto currencyDto = restTemplate.getForObject(url, CurrencyDto.class);
        return currencyDto;
    }
}
