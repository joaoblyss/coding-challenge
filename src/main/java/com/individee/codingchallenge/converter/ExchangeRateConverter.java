package com.individee.codingchallenge.converter;

import com.individee.codingchallenge.domain.ExchangeRate;
import com.individee.codingchallenge.domain.ECBDataset;
import com.individee.codingchallenge.xml.ECBCube;
import com.individee.codingchallenge.xml.ECBCube2;
import com.individee.codingchallenge.xml.ECBCube3;
import com.individee.codingchallenge.xml.ECBEnvelope;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class ExchangeRateConverter implements Converter<ECBEnvelope, ECBDataset> {

    @Override
    public ECBDataset convert(ECBEnvelope ecbEnvelope) {
        ECBDataset ECBDataset = new ECBDataset();
        ECBCube cube = ecbEnvelope.getCube();
        ECBCube2 cube2 = cube.getCube();
        Date date = cube2.getTime();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ECBDataset.setDate(localDate);
        List<ECBCube3> cubes = cube2.getCubes();
        List<ExchangeRate> currencies = cubes.stream().map(c -> new ExchangeRate(c.getCurrency(), c.getRate())).collect(Collectors.toList());
        ECBDataset.setExchangeRates(currencies);
        return ECBDataset;
    }

}
