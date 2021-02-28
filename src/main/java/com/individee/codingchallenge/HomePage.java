package com.individee.codingchallenge;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.individee.codingchallenge.domain.Ratings;
import com.individee.codingchallenge.service.RatingsService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.PatternValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WicketHomePage
public class HomePage extends WebPage {

    private static Logger logger = LoggerFactory.getLogger(HomePage.class);

    @SpringBean
    private RatingsService service;

    private final Label titleLabel;
    private final TextField<String> rateInput;
    private final DropDownChoice<String> currencyDropdownList;
    private final MultiLineLabel outputMessage;

    private final Model<String> rateInputModel;
    private final Model<String> outputMessageModel;
    private final Model<String> currencyDropdownListModel;

    public HomePage() {
        // instatiate models
        this.rateInputModel = new Model<>();
        this.outputMessageModel = new Model<>();
        this.currencyDropdownListModel = new Model<>();
        // instantiate components
        this.titleLabel = new Label("title", "Currency Exchange Calculator");
        this.rateInput = new TextField<>("rateInput", rateInputModel);
        this.rateInput.setRequired(true);
        this.currencyDropdownList = new DropDownChoice<>("rateOutputType");
        this.currencyDropdownList.setModel(this.currencyDropdownListModel);
        this.currencyDropdownList.setRequired(true);
        this.outputMessage = new MultiLineLabel("outputMessage", this.outputMessageModel);
        this.outputMessage.setOutputMarkupId(true);

        // initialize ratings map from the database
        Ratings ratings = this.service.findLast();
        Map<String, BigDecimal> ratingsMap = new HashMap<>();
        ratings.getCurrencies().forEach(currency -> ratingsMap.put(currency.getName(), currency.getExchangeRate()));
        this.currencyDropdownList.setChoices(new ArrayList<>(ratingsMap.keySet()));

        // setup validators
        PatternValidator notEmptyValidator = new PatternValidator(".+");// not empty
        this.currencyDropdownList.add(notEmptyValidator);
        PatternValidator decimalNumberValidator = new PatternValidator("\\d+(\\.\\d+)?");
        this.rateInput.add(decimalNumberValidator);

        // setup listeners
        this.rateInput.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                calculateOutputMessage(ratingsMap, target);
            }
        });
        this.currencyDropdownList.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                calculateOutputMessage(ratingsMap, target);
            }
        });


        // add components
        add(titleLabel);
        add(this.rateInput);
        add(this.currencyDropdownList);
        add(this.outputMessage);
    }

    private void calculateOutputMessage(Map<String, BigDecimal> ratingsMap, AjaxRequestTarget target) {
        if (rateInput.isValid() && currencyDropdownList.isValid()) {
            String value = rateInput.getDefaultModelObjectAsString();
            BigDecimal eurValue = new BigDecimal(value);
            String currencyName = HomePage.this.currencyDropdownList.getDefaultModelObjectAsString();
            BigDecimal currencyRate = ratingsMap.get(currencyName);
            BigDecimal result = eurValue.multiply(currencyRate);
            HomePage.this.outputMessageModel.setObject(String.format("%.2f EUR is equivalent to %.2f %s\n" +
                            "The exchange rate used was %.2f", eurValue.setScale(2, RoundingMode.HALF_UP),
                    result.setScale(2, RoundingMode.HALF_UP), currencyName,
                    currencyRate.setScale(2, RoundingMode.HALF_UP)));
            target.add(this.outputMessage);
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        this.validateAll();
    }

    private void validateAll() {
        this.rateInput.validate();
        this.currencyDropdownList.validate();
    }
}
