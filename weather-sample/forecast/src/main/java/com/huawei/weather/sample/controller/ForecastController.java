package com.huawei.weather.sample.controller;

import com.huawei.weather.sample.entity.objective.ForecastSummary;
import com.huawei.weather.sample.util.CacheUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastController {
    @Autowired
    CacheUtil cacheUtil;

    @RequestMapping(value = "/show")
    public ForecastSummary getForecast(@RequestParam("city") String city) {
        return cacheUtil.getForecastWeatherSummary(StringUtils.isNotBlank(city) ? city : "ShenZhen,CN");
    }
}
