package com.individee.codingchallenge.web.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.text.DecimalFormat;
import java.text.ParseException;

public class DecimalFormatValidator implements IValidator<String> {

    protected IValidationError decorate(IValidationError error, IValidatable<String> validatable) {
        return error;
    }

    @Override
    public void validate(IValidatable<String> validatable) {
        DecimalFormat df = new DecimalFormat();
        try {
            df.parse(validatable.getValue());
        } catch (ParseException e) {
            validatable.error(decorate(new ValidationError(this), validatable));
        }
    }
}
