package com.cource.ratio.service.courceratioservice;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@RestController
@RequestMapping("course-ratio/from/USD/to/")
public class CurrencyRateController {

    @Value("${currency.value}")
    private String currencyFromProperties;

    private final CurrencyRateProxy rateProxy;
    private final GiphyProxy giphyProxy;

    public CurrencyRateController(CurrencyRateProxy rateProxy, GiphyProxy giphyProxy) {
        this.rateProxy = rateProxy;
        this.giphyProxy = giphyProxy;
    }

    @GetMapping("${currency.value}")
    public ModelAndView getCourseRatioFromProperties() {

        JSONObject currentRateJSON = new JSONObject(rateProxy.getCurrencyRates(currencyFromProperties));
        JSONObject historicalRateJSON = new JSONObject(rateProxy.getHistoricalRate(LocalDate.now().minusDays(2).toString(), currencyFromProperties));
        double currentValue = Double.parseDouble(currentRateJSON.getJSONObject("rates").get(currencyFromProperties).toString());
        double historicValue = Double.parseDouble(historicalRateJSON.getJSONObject("rates").get(currencyFromProperties).toString());
        String tag = currentValue > historicValue? "rich" : "broke";

        return new ModelAndView("redirect:" + new JSONObject(giphyProxy.getGifByTag(tag)).getJSONObject("data").get("url").toString());
    }

    @GetMapping("{currency}")
    public ModelAndView getCourseRatioFromEndpoint(@PathVariable("currency") String currencyFromEndpoint) {

        JSONObject currentRateJSON = new JSONObject(rateProxy.getCurrencyRates(currencyFromEndpoint));
        JSONObject historicalRateJSON = new JSONObject(rateProxy.getHistoricalRate(LocalDate.now().minusDays(2).toString(), currencyFromEndpoint));
        double currentValue = Double.parseDouble(currentRateJSON.getJSONObject("rates").get(currencyFromEndpoint).toString());
        double historicValue = Double.parseDouble(historicalRateJSON.getJSONObject("rates").get(currencyFromEndpoint).toString());
        String tag = currentValue > historicValue? "rich" : "broke";

        return new ModelAndView("redirect:" + new JSONObject(giphyProxy.getGifByTag(tag)).getJSONObject("data").get("url").toString());
    }
}
