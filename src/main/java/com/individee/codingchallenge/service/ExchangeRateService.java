package com.individee.codingchallenge.service;

import com.individee.codingchallenge.domain.ECBDataset;
import com.individee.codingchallenge.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;

@Service
public class ExchangeRateService {

    private ExchangeRateRepository repository;

    @Inject
    public void setRepository(ExchangeRateRepository repository) {
        this.repository = repository;
    }

    public ECBDataset findLast() {
        return this.repository.findFirstOrderByDate();
    }

    public ECBDataset findByDate(LocalDate date) {
        return this.repository.findRatingsByDate(date);
    }

    public void save(ECBDataset ECBDataset) {
        this.repository.save(ECBDataset);
    }
}
