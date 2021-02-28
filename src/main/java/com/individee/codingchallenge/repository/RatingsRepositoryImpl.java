package com.individee.codingchallenge.repository;

import com.individee.codingchallenge.domain.Ratings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.inject.Inject;

public class RatingsRepositoryImpl implements RatingsQuery {

    private MongoTemplate template;

    @Inject
    public void setTemplate(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public Ratings findFirstOrderByDate() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "date"));
//        query.with(PageRequest.of(0, 1));
        return template.findOne(query, Ratings.class);
    }

}
