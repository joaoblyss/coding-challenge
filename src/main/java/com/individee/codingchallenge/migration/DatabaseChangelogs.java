package com.individee.codingchallenge.migration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.individee.codingchallenge.domain.ECBDataset;
import com.individee.codingchallenge.domain.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;

@ChangeLog(order = "01")
public class DatabaseChangelogs {

    @ChangeSet(order = "01", id = "initDatabase", author = "mongobee")
    public void initDatabase(MongockTemplate template) {
        ECBDataset dataset = new ECBDataset();
        dataset.setDate(LocalDate.of(2021, 3, 3));
        LinkedList<ExchangeRate> rates = new LinkedList<>();
        rates.add(new ExchangeRate("USD", BigDecimal.valueOf(1.2048)));
        rates.add(new ExchangeRate("JPY", BigDecimal.valueOf(128.81)));
        rates.add(new ExchangeRate("BGN", BigDecimal.valueOf(1.9558)));
        rates.add(new ExchangeRate("CZK", BigDecimal.valueOf(26.138)));
        rates.add(new ExchangeRate("DKK", BigDecimal.valueOf(7.4362)));
        rates.add(new ExchangeRate("GBP", BigDecimal.valueOf(0.86351)));
        rates.add(new ExchangeRate("HUF", BigDecimal.valueOf(363.75)));
        rates.add(new ExchangeRate("PLN", BigDecimal.valueOf(4.5336)));
        rates.add(new ExchangeRate("RON", BigDecimal.valueOf(4.8775)));
        rates.add(new ExchangeRate("SEK", BigDecimal.valueOf(10.1250)));
        rates.add(new ExchangeRate("CHF", BigDecimal.valueOf(1.1064)));
        rates.add(new ExchangeRate("ISK", BigDecimal.valueOf(152.50)));
        rates.add(new ExchangeRate("NOK", BigDecimal.valueOf(10.2451)));
        rates.add(new ExchangeRate("HRK", BigDecimal.valueOf(7.5805)));
        rates.add(new ExchangeRate("RUB", BigDecimal.valueOf(88.8857)));
        rates.add(new ExchangeRate("TRY", BigDecimal.valueOf(8.9009)));
        rates.add(new ExchangeRate("AUD", BigDecimal.valueOf(1.5455)));
        rates.add(new ExchangeRate("BRL", BigDecimal.valueOf(6.8827)));
        rates.add(new ExchangeRate("CAD", BigDecimal.valueOf(1.5234)));
        rates.add(new ExchangeRate("CNY", BigDecimal.valueOf(7.7897)));
        rates.add(new ExchangeRate("HKD", BigDecimal.valueOf(9.3452)));
        rates.add(new ExchangeRate("IDR", BigDecimal.valueOf(17175.15)));
        rates.add(new ExchangeRate("ILS", BigDecimal.valueOf(3.9640)));
        rates.add(new ExchangeRate("INR", BigDecimal.valueOf(87.7665)));
        rates.add(new ExchangeRate("KRW", BigDecimal.valueOf(1347.23)));
        rates.add(new ExchangeRate("MXN", BigDecimal.valueOf(24.9919)));
        rates.add(new ExchangeRate("MYR", BigDecimal.valueOf(4.8806)));
        rates.add(new ExchangeRate("NZD", BigDecimal.valueOf(1.6581)));
        rates.add(new ExchangeRate("PHP", BigDecimal.valueOf(58.420)));
        rates.add(new ExchangeRate("SGD", BigDecimal.valueOf(1.6041)));
        rates.add(new ExchangeRate("THB", BigDecimal.valueOf(36.566)));
        rates.add(new ExchangeRate("ZAR", BigDecimal.valueOf(18.0073)));
        dataset.setExchangeRates(rates);

        template.save(dataset);
    }

}
