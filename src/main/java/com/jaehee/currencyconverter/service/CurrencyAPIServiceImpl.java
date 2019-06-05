package com.jaehee.currencyconverter.service;

import com.jaehee.currencyconverter.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// 다른 외부 API를 사용하게 될 경우를 대비해 Interface에 기능을 선언하고 이를 구현함
@Service
@RequiredArgsConstructor
public class CurrencyAPIServiceImpl implements CurrencyAPIService {
    private final RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    //외부 API에 접속해서 환율 정보를 DTO 형식으로 가져오기
    @Override
    public CurrencyDto getCurrencyDto() {
        String url = environment.getProperty("currencyLayer.url")+environment.getProperty("currencyLayer.key");
        CurrencyDto currencyDto = restTemplate.getForObject(url, CurrencyDto.class);
        return currencyDto;
    }
}
