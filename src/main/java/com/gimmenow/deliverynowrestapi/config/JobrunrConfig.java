package com.gimmenow.deliverynowrestapi.config;

import com.gimmenow.deliverynowrestapi.service.WeatherJobService;
import com.gimmenow.deliverynowrestapi.service.WeatherService;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobrunrConfig {
    @Bean
    public StorageProvider storageProvider(JobMapper jobMapper) {
        InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }
    
    @Bean
    public WeatherService weatherService() {
        return new WeatherService();
    }
    
    @Bean
    public WeatherJobService weatherJobService() {
        return new WeatherJobService();
    }
}
