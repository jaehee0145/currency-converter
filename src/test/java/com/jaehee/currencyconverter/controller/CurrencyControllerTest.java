package com.jaehee.currencyconverter.controller;

import com.jaehee.currencyconverter.dto.RequestDto;
import com.jaehee.currencyconverter.service.CurrencyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurrencyControllerTest {

    @Autowired
    private CurrencyApiController currencyApiController;

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void API요청으로_송금액_계산하기(){
//        RequestDto requestDto = new RequestDto();
//        requestDto.setAmount(50.0);
//        requestDto.setQuoteCurrency("KRW");
//        Double remittance1 = currencyApiController.getRemittance(requestDto);
//
//        Double exchangeRate = currencyService.getExchangeRate("KRW");
//        Double remittance2 = 50.0 * exchangeRate;
//        Assert.assertEquals(remittance1, remittance2);
    }
}
