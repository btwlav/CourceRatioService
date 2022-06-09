package com.cource.ratio.service.courceratioservice;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@RestController
public class CurrencyRateController {

    private final CurrencyRateProxy rateProxy;
    private final GiphyProxy giphyProxy;

    public CurrencyRateController(CurrencyRateProxy rateProxy, GiphyProxy giphyProxy) {
        this.rateProxy = rateProxy;
        this.giphyProxy = giphyProxy;
    }

    @GetMapping(value = "course-ratio/from/USD/to/{currency}")
    public String getCourseRatio(@PathVariable("currency") String currency) {

        JSONObject currentRateJSON = new JSONObject(rateProxy.getCurrencyRates(currency));
        JSONObject historicalRateJSON = new JSONObject(rateProxy.getHistoricalRate(LocalDate.now().minusDays(2).toString(), currency));
        Object currentValue = currentRateJSON.getJSONObject("rates").get(currency);
        Object historicValue = historicalRateJSON.getJSONObject("rates").get(currency);
        if (!currentValue.equals(historicValue)) {
            return new JSONObject(giphyProxy.getGifRatio("rich")).toString();
        }

        return new JSONObject(giphyProxy.getGifRatio("broke")).toString();
    }
}
