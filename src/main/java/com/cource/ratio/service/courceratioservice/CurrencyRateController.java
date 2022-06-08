package com.cource.ratio.service.courceratioservice;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

@RestController
public class CurrencyRateController {

    private final CurrencyRateProxy proxy;

    public CurrencyRateController(CurrencyRateProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping(value = "course-ratio/from/USD/to/{currency}")
    public ModelAndView getCourseRatio(@PathVariable("currency") String currency) {

        JSONObject currentRateJSON = new JSONObject(proxy.getCurrencyRates(currency));
        JSONObject historicalRateJSON = new JSONObject(proxy.getHistoricalRate(LocalDate.now().minusDays(2).toString(), currency));
        Object currentValue = currentRateJSON.getJSONObject("rates").get(currency);
        Object historicValue = historicalRateJSON.getJSONObject("rates").get(currency);
//        System.out.println(rate);
        if (!currentValue.equals(historicValue)) {
            return new ModelAndView("redirect:https://api.giphy.com/v1/gifs/random?api_key=3ACtdhmayOh9xyMYDCZol32dCV1Bf4oV&tag=rich");
        }

        return new ModelAndView("redirect:https://api.giphy.com/v1/gifs/random?api_key=3ACtdhmayOh9xyMYDCZol32dCV1Bf4oV&tag=broke");
    }
}
