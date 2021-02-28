package com.individee.codingchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.individee.codingchallenge.repository"})
public class ExchangeRatesCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRatesCalculatorApplication.class, args);
    }

}
