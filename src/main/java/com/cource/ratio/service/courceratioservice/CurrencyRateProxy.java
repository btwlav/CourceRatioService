package com.cource.ratio.service.courceratioservice;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="openexchangerate", url="https://openexchangerates.org")
public interface CurrencyRateProxy {
    @GetMapping("api/latest.json?app_id=b933a73d175a4aaf954d513e39e1a4bf&symbols={currency}")
    String getCurrencyRates(@PathVariable("currency") String currency);

    @GetMapping("api/historical/{date}.json?app_id=b933a73d175a4aaf954d513e39e1a4bf&symbols={currency}")
    String getHistoricalRate(@PathVariable("date") String date, @PathVariable("currency") String currency);
}
