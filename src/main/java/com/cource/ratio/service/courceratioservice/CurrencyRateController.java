package com.cource.ratio.service.courceratioservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyRateController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CurrencyRateProxy proxy;

    public CurrencyRateController(CurrencyRateProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping(value = "course-ratio/from/USD/to/{currency}")
    public JSONObject getCourseRatio(@PathVariable("currency") String currency) throws JsonProcessingException {

        JSONObject response = proxy.getCurrencyRates(currency);
        logger.info("{}", response);
////        JSONArray ratesArray = (JSONArray) response.get("rates");
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(response.toJSONString());
        Double rate = 62.05;//jsonNode.get("RUB").asDouble();
        return response;
//        return new CurrencyRate("11", "USD", rate);
//        return new CurrencyRate(response.get("timestamp").toString(),
//                response.get("base").toString(),
//                rate);
    }
}
