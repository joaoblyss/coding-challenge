package com.individee.codingchallenge;

import com.individee.codingchallenge.config.ECBProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(ECBProperties.class)
@EnableMongoRepositories(basePackages = {"com.individee.codingchallenge.repository"})
public class ExchangeRatesCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRatesCalculatorApplication.class, args);
    }

}
