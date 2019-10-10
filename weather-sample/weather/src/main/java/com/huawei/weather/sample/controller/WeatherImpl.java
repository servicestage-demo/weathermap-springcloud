package com.huawei.weather.sample.controller;

import com.huawei.weather.sample.entity.objective.CurrentWeatherSummary;
import com.huawei.weather.sample.util.CacheUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherImpl {

    @Autowired
    CacheUtil cacheUtil;

    @RequestMapping(value = "/show")
    public CurrentWeatherSummary showCurrentWeather(@RequestParam("city") String city) {
        return cacheUtil.getCurrentWeatherSummary(StringUtils.isNotBlank(city) ? city : "shenzhen,cn");
    }
}
