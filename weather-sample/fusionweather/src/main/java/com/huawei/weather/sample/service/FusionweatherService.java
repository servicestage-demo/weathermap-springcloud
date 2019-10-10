package com.huawei.weather.sample.service;


import com.huawei.weather.sample.entity.CurrentWeatherSummary;
import com.huawei.weather.sample.entity.ForecastWeatherSummary;
import com.huawei.weather.sample.entity.FusionWeatherSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class FusionweatherService {
    @Autowired
    RestTemplate restTemplate;

    public FusionWeatherSummary showFusionWeather(String city, String user) {
        FusionWeatherSummary summary = new FusionWeatherSummary();
        summary.setCurrentWeather(achieveCurrentWeatherSummary(city, user));
        summary.setForecastWeather(achieveForecastWeatherSummary(city));
        return summary;
    }

    private CurrentWeatherSummary achieveCurrentWeatherSummary(String city, String user) {
        String url = "http://weather/show?city=" + city;
        return restTemplate.getForObject(StringUtils.isEmpty(user) ? url + "&user=" + user : url, CurrentWeatherSummary.class);
    }

    private ForecastWeatherSummary achieveForecastWeatherSummary(String city) {
        final String url = "http://forecast/show?city=" + city;
        return restTemplate.getForObject(url, ForecastWeatherSummary.class);
    }
}
