package com.individee.codingchallenge.xml;

import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;

public class ECBCube3 {

    private String currency;
    private BigDecimal rate;

    @XmlAttribute(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlAttribute(name = "rate")
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
