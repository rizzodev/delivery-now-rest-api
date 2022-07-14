package com.gimmenow.deliverynowrestapi.controller;

import com.gimmenow.deliverynowrestapi.dto.OpenWeather;
import com.gimmenow.deliverynowrestapi.service.WeatherService;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping("/{city}")
    public OpenWeather getWeatherByCity(@PathVariable("city") String city) {
        return service.getWeatherByCity(city);
    }

    @PostMapping("/run-job")
    public ResponseEntity<String> runJob(@RequestParam String city) {
        service.runWeatherJobByCity(city);
        return ResponseEntity.ok("Job Executed !");
    }
}
