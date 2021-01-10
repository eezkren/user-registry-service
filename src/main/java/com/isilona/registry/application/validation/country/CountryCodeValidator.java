package com.isilona.registry.application.validation.country;

import com.isilona.registry.domain.service.validation.country.CountryCodeValidationService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

public class CountryCodeValidator implements ConstraintValidator<CountryCodeConstraint, String> {


    private final CountryCodeValidationService validatorService;

    public CountryCodeValidator(CountryCodeValidationService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public void initialize(CountryCodeConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String countryCodeField, ConstraintValidatorContext cxt) {
        boolean isValid = validatorService.isValid(countryCodeField);
        if (!isValid) {
            ((ConstraintValidatorContextImpl) cxt).addMessageParameter("wrongValue", countryCodeField.toUpperCase());
        }
        return isValid;
    }

}