package com.cource.ratio.service.courceratioservice;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
class CurrencyRateControllerTest {

    @MockBean
    private CurrencyRateProxy rateProxy;

    @MockBean
    private GiphyProxy giphyProxy;

    MockitoSession session;

    @BeforeTestMethod
    public void beforeMethod() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @Test
    void getCourseRatio() {

        JSONObject jsonObject = new JSONObject();

        //получение текущего курса
        Mockito.when(rateProxy.getCurrencyRates(Mockito.any()))
                .thenReturn(String.valueOf(Optional.of(jsonObject)));
        //получение неактуального курса
        Mockito.when(rateProxy.getHistoricalRate(Mockito.any(), Mockito.any()))
                .thenReturn(String.valueOf(Optional.of(jsonObject)));
        //получение гифки
        Mockito.when(giphyProxy.getGifByTag(Mockito.any()))
                .thenReturn(String.valueOf(Optional.of(jsonObject)));

    }

    @AfterTestMethod
    public void afterMethod() {
        session.finishMocking();
    }
}