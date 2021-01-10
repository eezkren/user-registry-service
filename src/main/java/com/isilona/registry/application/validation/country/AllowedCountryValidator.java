package com.isilona.registry.application.validation.country;

import com.isilona.registry.domain.service.validation.country.AllowedCountryValidationService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

public class AllowedCountryValidator implements ConstraintValidator<AllowedCountryConstraint, String> {

    private final AllowedCountryValidationService validatorService;

    public AllowedCountryValidator(AllowedCountryValidationService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public void initialize(AllowedCountryConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String countryCodeField, ConstraintValidatorContext cxt) {
        boolean isValid = validatorService.isValid(countryCodeField);
        if (!isValid) {
            ((ConstraintValidatorContextImpl) cxt).addMessageParameter("yourCountry", countryCodeField.toUpperCase());
        }
        return isValid;
    }

}