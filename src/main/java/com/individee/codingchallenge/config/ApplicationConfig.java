package com.individee.codingchallenge.config;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.MongoDBConfiguration;
import com.github.cloudyrock.spring.v5.EnableMongock;
import com.individee.codingchallenge.config.validator.ECBPropertiesValidator;
import com.mongodb.ReadConcernLevel;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableMongock
@EnableScheduling
@EnableConfigurationProperties(ECBProperties.class)
@EnableMongoRepositories(basePackages = {"com.individee.codingchallenge.repository"})
public class ApplicationConfig {

    @Bean
    public static ECBPropertiesValidator configurationPropertiesValidator() {
        return new ECBPropertiesValidator();
    }

    @Bean
    public MongoDBConfiguration mongockConfiguration() {
        MongoDBConfiguration mongoDBConfiguration = new MongoDBConfiguration();
        mongoDBConfiguration.setReadConcern(ReadConcernLevel.LOCAL);
        return mongoDBConfiguration;
    }

}
