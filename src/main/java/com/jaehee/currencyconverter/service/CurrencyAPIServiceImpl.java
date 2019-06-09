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
    private CurrencyDto currencyDto;

    //외부 API에 요청을 보내서 환율 정보를 DTO 형식으로 가져오기
    @Override
    public CurrencyDto getCurrencyDto() {
        String url = environment.getProperty("currencyLayer.url") + environment.getProperty("currencyLayer.key");
        currencyDto = restTemplate.getForObject(url, CurrencyDto.class);

        validateCurrencyDto();
        return currencyDto;
    }

    private void validateCurrencyDto() {
        if (currencyDto == null) {
            throw new RestClientException("API Error");
        } else if (!currencyDto.isSuccess()) {
            throw new RestClientException("API Error- " + currencyDto.getError().get("code") + " : "
                    + currencyDto.getError().get("info"));
            //source가 "USD"가 아니면 Exception
        } else if (!currencyDto.getSource().equals("USD")) {
            throw new RestClientException("API Error- 환율 정보 source를 확인하세요");
        }

    }
}
