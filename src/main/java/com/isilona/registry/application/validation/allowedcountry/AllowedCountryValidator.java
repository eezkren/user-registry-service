package com.isilona.registry.application.validation.allowedcountry;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.springframework.beans.factory.annotation.Value;

public class AllowedCountryValidator implements ConstraintValidator<AllowedCountryConstraint, String> {

    private final List<String> allowedCountries;

    AllowedCountryValidator(@Value("${validation.allowed-countries}") List<String> allowedCountries) {
        this.allowedCountries = allowedCountries;
    }

    @Override
    public void initialize(AllowedCountryConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String countryCodeField, ConstraintValidatorContext cxt) {
        if (countryCodeField == null || countryCodeField.trim().length() == 0) {
            return true;
        }

        if (!allowedCountries.contains(countryCodeField.toUpperCase())) {
            ((ConstraintValidatorContextImpl) cxt).addMessageParameter("yourCountry", countryCodeField.toUpperCase());
            return false;
        }

        return true;
    }

}