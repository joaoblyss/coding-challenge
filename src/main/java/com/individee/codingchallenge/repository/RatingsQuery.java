package com.individee.codingchallenge.repository;

import com.individee.codingchallenge.domain.Ratings;

public interface RatingsQuery {

    Ratings findFirstOrderByDate();

}
