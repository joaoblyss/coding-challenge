package com.individee.codingchallenge.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document
public class ExchangeRate {

    private String currency;
    private BigDecimal exchangeRate;

    public ExchangeRate(String currency, BigDecimal exchangeRate) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    @Field("currency")
    public String getName() {
        return currency;
    }

    public void setName(String name) {
        this.currency = name;
    }

    @Field("rate")
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
