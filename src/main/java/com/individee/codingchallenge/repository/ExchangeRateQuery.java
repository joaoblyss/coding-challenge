package com.individee.codingchallenge.repository;

import com.individee.codingchallenge.domain.ECBDataset;

public interface ExchangeRateQuery {

    ECBDataset findFirstOrderByDate();

}
