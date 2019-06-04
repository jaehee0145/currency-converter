package com.jaehee.currencyconverter.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        //TODO timeout 메서드 다시 확인해보기
        builder.setConnectTimeout(10*1000);
        builder.setReadTimeout(10*1000);
        return builder.build();
    }
}
