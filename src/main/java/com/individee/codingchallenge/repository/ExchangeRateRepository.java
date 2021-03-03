package com.individee.codingchallenge.repository;

import com.individee.codingchallenge.domain.ECBDataset;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface ExchangeRateRepository extends MongoRepository<ECBDataset, LocalDate>, ExchangeRateQuery {

    ECBDataset findRatingsByDate(LocalDate date);
}
