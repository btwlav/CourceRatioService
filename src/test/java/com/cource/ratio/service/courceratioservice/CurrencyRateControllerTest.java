package com.cource.ratio.service.courceratioservice;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class CurrencyRateControllerTest {

    @Autowired
    private CurrencyRateProxy rateProxy;

    @Autowired
    private GiphyProxy giphyProxy;

    @Test
    void getCourseRatio() {

        JSONObject response = new JSONObject(rateProxy.getCurrencyRates("RUB"));

        Assert.assertNotNull(response);
    }
}