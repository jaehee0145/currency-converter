package com.jaehee.currencyconverter.service;

import com.jaehee.currencyconverter.dto.CurrencyDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurrencyAPIServiceTest {
    @Autowired
    private CurrencyAPIServiceImpl currencyAPIService;

    @Test
    public void 외부API를_호출해서_환율정보_가져오기(){
        CurrencyDto currencyDto = currencyAPIService.getCurrencyDto();
        Assert.assertNotNull(currencyDto);
//        Assert.assertNull(currencyDto);
    }

}
