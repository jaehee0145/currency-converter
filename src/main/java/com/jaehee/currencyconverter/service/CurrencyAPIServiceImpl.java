package com.jaehee.currencyconverter.service;

import com.jaehee.currencyconverter.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

// 다른 외부 API를 사용하게 될 경우를 대비해 Interface에 기능을 선언하고 이를 구현함
@Service
@RequiredArgsConstructor
public class CurrencyAPIServiceImpl implements CurrencyAPIService {
    private final RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    //외부 API에 요청을 보내서 환율 정보를 DTO 형식으로 가져오기
    @Override
    public CurrencyDto getCurrencyDto() {
        String url = environment.getProperty("currencyLayer.url") + environment.getProperty("currencyLayer.key");
        //TODO 3개 국가에 대한 정보만 저장하는게 나을까? enum 클래스 이용해서? 
        CurrencyDto currencyDto = restTemplate.getForObject(url, CurrencyDto.class);
        //source가 "USD"인 경우에만 currencyDTO를 리턴
        if (currencyDto.getSource().equals("USD") ) {
            return currencyDto;
        } else {
            throw new RestClientException("API Error");
        }
    }
}
