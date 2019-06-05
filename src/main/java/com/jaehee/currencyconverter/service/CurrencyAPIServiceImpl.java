package com.jaehee.currencyconverter.service;

import com.jaehee.currencyconverter.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CurrencyAPIServiceImpl implements CurrencyAPIService {
    private final RestTemplate restTemplate;

    @Autowired
    private Environment environment;


    @Override
    public CurrencyDto getCurrencyDto() {
        String url = environment.getProperty("currencyLayer.url")+environment.getProperty("currencyLayer.url");
        CurrencyDto currencyDto = restTemplate.getForObject(url, CurrencyDto.class);
        return currencyDto;
    }
}
