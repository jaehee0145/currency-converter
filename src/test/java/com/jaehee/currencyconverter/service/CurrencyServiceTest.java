package com.jaehee.currencyconverter.service;

import com.jaehee.currencyconverter.dto.CurrencyDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurrencyServiceTest {
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyAPIService currencyAPIService;

    @Test
    public void 수취국가에_따라_환율정보_가져오기(){
        Double exchangeRate1 = currencyService.getExchangeRate("KRW");
        CurrencyDto currencyDto = currencyAPIService.getCurrencyDto();
        Double exchangeRate2 = currencyDto.getQuotes().get("USDKRW");
        Assert.assertEquals(exchangeRate1, exchangeRate2, 0);
    }

    @Test
    public void 환율과_금액으로_송금액_계산하기(){
        Double remittance = currencyService.getRemittance(100.00, 50);
        Assert.assertEquals(remittance, 5000.00, 0);
    }
}


