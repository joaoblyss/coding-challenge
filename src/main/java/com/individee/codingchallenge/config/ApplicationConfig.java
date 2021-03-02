package com.individee.codingchallenge.config;

import com.individee.codingchallenge.config.validator.ECBPropertiesValidator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
@EnableConfigurationProperties(ECBProperties.class)
@EnableMongoRepositories(basePackages = {"com.individee.codingchallenge.repository"})
public class ApplicationConfig {

    @Bean
    public static ECBPropertiesValidator configurationPropertiesValidator() {
        return new ECBPropertiesValidator();
    }

}
