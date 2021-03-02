package com.individee.codingchallenge;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.individee.codingchallenge.domain.Ratings;
import com.individee.codingchallenge.service.RatingsService;
import com.individee.codingchallenge.validator.DecimalFormatValidator;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WicketHomePage
public class HomePage extends WebPage {

    private static Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

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
        this.currencyDropdownList = new DropDownChoice<>("rateOutputType");
        this.currencyDropdownList.setModel(this.currencyDropdownListModel);
        this.currencyDropdownList.setRequired(true);
        this.outputMessage = new MultiLineLabel("outputMessage", this.outputMessageModel);
        this.outputMessage.setOutputMarkupId(true);
        this.outputMessage.setEscapeModelStrings(false);

        // initialize ratings map from the database
        Ratings ratings = this.service.findLast();
        Map<String, BigDecimal> ratingsMap = new HashMap<>();
        ratings.getCurrencies().forEach(currency -> ratingsMap.put(currency.getName(), currency.getExchangeRate()));
        this.currencyDropdownList.setChoices(new ArrayList<>(ratingsMap.keySet()));

        // setup validators
        this.currencyDropdownList.add(new PatternValidator(".+"));
        this.rateInput.add(new DecimalFormatValidator());

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
        add(this.titleLabel);
        add(this.rateInput);
        add(this.currencyDropdownList);
        add(this.outputMessage);
    }

    private void calculateOutputMessage(Map<String, BigDecimal> ratingsMap, AjaxRequestTarget target) {
        String resultMessage = "";
        if (this.currencyDropdownList.isValid()) {
            String value = this.rateInput.getDefaultModelObjectAsString();
            if (!value.isBlank()) {
                try {
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setParseBigDecimal(true);
                    BigDecimal eurValue = (BigDecimal) decimalFormat.parse(value);
                    String currencyName = HomePage.this.currencyDropdownList.getDefaultModelObjectAsString();
                    BigDecimal currencyRate = ratingsMap.get(currencyName);
                    BigDecimal result = eurValue.multiply(currencyRate);
                    resultMessage = String.format("<b>%.2f EUR</b> is equivalent to <b>%.2f %s</b>\n" +
                                    "The exchange rate used was <b>%.2f</b>", eurValue.setScale(2, RoundingMode.HALF_UP),
                            result.setScale(2, RoundingMode.HALF_UP), currencyName,
                            currencyRate.setScale(2, RoundingMode.HALF_UP));
                } catch (ParseException e) {
                    // shouldn't happen, since the DecimalFormatValidator won't allow it
                    LOGGER.error("failed to parse input rate value", e);
                }
            }
        }
        HomePage.this.outputMessageModel.setObject(resultMessage);
        target.add(this.outputMessage);
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
