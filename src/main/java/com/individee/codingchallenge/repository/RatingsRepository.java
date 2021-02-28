package com.individee.codingchallenge.repository;

import com.individee.codingchallenge.domain.Ratings;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface RatingsRepository extends MongoRepository<Ratings, LocalDate>, RatingsQuery {

    Ratings findRatingsByDate(LocalDate date);
}
