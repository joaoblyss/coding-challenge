package com.individee.codingchallenge.repository;

import com.individee.codingchallenge.domain.ECBDataset;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.inject.Inject;

public class ExchangeRateRepositoryImpl implements ExchangeRateQuery {

    private MongoTemplate template;

    @Inject
    public void setTemplate(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public ECBDataset findFirstOrderByDate() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "date"));
        return template.findOne(query, ECBDataset.class);
    }

}
