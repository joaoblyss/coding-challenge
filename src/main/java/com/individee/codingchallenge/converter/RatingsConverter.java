package com.individee.codingchallenge.converter;

import com.individee.codingchallenge.domain.Currency;
import com.individee.codingchallenge.domain.Ratings;
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
public class RatingsConverter implements Converter<ECBEnvelope, Ratings> {

    @Override
    public Ratings convert(ECBEnvelope ecbEnvelope) {
        Ratings ratings = new Ratings();
        ECBCube cube = ecbEnvelope.getCube();
        ECBCube2 cube2 = cube.getCube();
        Date date = cube2.getTime();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ratings.setDate(localDate);
        List<ECBCube3> cubes = cube2.getCubes();
        List<Currency> currencies = cubes.stream().map(c -> new Currency(c.getCurrency(), c.getRate())).collect(Collectors.toList());
        ratings.setCurrencies(currencies);
        return ratings;
    }

}
