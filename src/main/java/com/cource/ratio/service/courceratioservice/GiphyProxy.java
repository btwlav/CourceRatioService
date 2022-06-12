package com.cource.ratio.service.courceratioservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="giphy", url="https://api.giphy.com/")
public interface GiphyProxy {

    @GetMapping("/v1/gifs/random?api_key=3ACtdhmayOh9xyMYDCZol32dCV1Bf4oV&tag={tag}")
    String getGifByTag(@PathVariable("tag") String tag);
}
