package com.individee.codingchallenge.service;

import com.individee.codingchallenge.domain.Ratings;
import com.individee.codingchallenge.repository.RatingsRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RatingsService {

    private RatingsRepository repository;

    @Inject
    public void setRepository(RatingsRepository repository) {
        this.repository = repository;
    }

    public Ratings findLast() {
        return this.repository.findFirstOrderByDate();
    }
}
