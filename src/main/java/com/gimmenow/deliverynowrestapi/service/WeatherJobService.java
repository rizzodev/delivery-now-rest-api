package com.gimmenow.deliverynowrestapi.service;

import com.gimmenow.deliverynowrestapi.dto.OpenWeather;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherJobService {
    Logger log = new JobRunrDashboardLogger(LoggerFactory.getLogger(getClass()));

    @Job(name = "Weather every 1 min")
    public void execute(OpenWeather weather) {
        log.info(String.format("Weather in %s now is %s (%s)", weather.getName(),weather.getWeather()[0].getMain(),weather.getWeather()[0].getDescription()));
    }

}
