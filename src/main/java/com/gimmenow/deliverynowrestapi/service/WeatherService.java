package com.gimmenow.deliverynowrestapi.service;

import com.gimmenow.deliverynowrestapi.dto.OpenWeather;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.cron.Cron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherJobService job;
    private Logger log = LoggerFactory.getLogger(getClass());

    public OpenWeather getWeatherByCity(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
        ResponseEntity<OpenWeather> response = restTemplate
                .getForEntity(url, OpenWeather.class);
        return response.getBody();
    }

    public void runWeatherJobByCity(String city) {
        log.info("Print weather every 1 min now started...");
        BackgroundJob.scheduleRecurrently(Cron.minutely(), () -> job.execute(getWeatherByCity(city)));
    }

}
