package com.huawei.weather.sample.controller;

import com.huawei.weather.sample.entity.FusionWeatherSummary;
import com.huawei.weather.sample.service.FusionweatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/fusionweather")
public class FusionWeatherController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private FusionweatherService fusionweatherdataService;

    @RequestMapping("/show")
    public FusionWeatherSummary show(@RequestParam("city") String city, @RequestParam(value = "user", required = false) String user) {
        return fusionweatherdataService.showFusionWeather(city, user);
    }
}
