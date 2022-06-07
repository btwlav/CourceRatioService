package com.cource.ratio.service.courceratioservice;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyRate {
    String timeStamp;
    String base;
    Double rate;

    public CurrencyRate(String timeStamp, String base, Double rate) {
        this.timeStamp = timeStamp;
        this.base = base;
        this.rate = rate;
    }
}
