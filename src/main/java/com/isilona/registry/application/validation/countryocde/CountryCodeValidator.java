package com.isilona.registry.application.validation.countryocde;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Arrays;
import java.util.Locale;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

public class CountryCodeValidator implements ConstraintValidator<CountryCodeConstraint, String> {

    @Override
    public void initialize(CountryCodeConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String countryCodeField, ConstraintValidatorContext cxt) {
        if (isBlank(countryCodeField)) {
            return true;
        }

        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCodeField.toUpperCase())) {
            ((ConstraintValidatorContextImpl) cxt).addMessageParameter("wrongValue", countryCodeField);
            return false;
        }

        return true;
    }

}