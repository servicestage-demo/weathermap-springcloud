package com.huawei.weather.sample.util;

import com.huawei.weather.sample.entity.objective.CurrentWeatherSummary;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache
 */
@Component
public class CacheUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheUtil.class);

    private static final int TIME_SPAN = 1800 * 1000;

    private Map<String, CurrentWeatherSummary> cacheMap = new ConcurrentHashMap<String, CurrentWeatherSummary>();

    @Autowired
    private OpenWeatherMapClient openWeatherMapClient;
    
    public CurrentWeatherSummary getCurrentWeatherSummary(String kk)
    {
        CurrentWeatherSummary su = cacheMap.computeIfAbsent(kk, (nm) -> {
            LOGGER.info("Achieve the forecast weather data from OpenWeatherMap");
            return openWeatherMapClient.showCurrentWeather(nm);
        });

        if (System.currentTimeMillis() - su.getCurrentTime() > TIME_SPAN)
        {
            su = cacheMap.computeIfPresent(kk, (nm, vl) -> {
                LOGGER.info("Retrieve the forecast weather data from OpenWeatherMap");
                return openWeatherMapClient.showCurrentWeather(nm);
            });
        }

        if (StringUtils.isBlank(su.getCityName()))
        {
            cacheMap.remove(kk);
        }

        return su;
    }
}
