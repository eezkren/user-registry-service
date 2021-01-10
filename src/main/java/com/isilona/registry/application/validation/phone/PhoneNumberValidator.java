package com.isilona.registry.application.validation.phone;


import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.isilona.registry.application.request.CreateRegistrationRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, CreateRegistrationRequest> {

    private final PhoneNumberValidationStrategyFactory phoneNumberValidationStrategyFactory = new PhoneNumberValidationStrategyFactory();

    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(CreateRegistrationRequest createRegistrationRequest, ConstraintValidatorContext constraintValidatorContext) {

        if (skipValidation(createRegistrationRequest)) {
            return true;
        }

        String formattedPhoneNumber = format(createRegistrationRequest.getPhone());
        String country = createRegistrationRequest.getCountry();

        PhoneNumberValidationStrategy phoneNumberValidationStrategy = phoneNumberValidationStrategyFactory.getPhoneNumberValidationStrategy(country);
        return phoneNumberValidationStrategy.isValid(formattedPhoneNumber);
    }


    private String format(String input) {
        return "+" + input.replaceAll("[^a-zA-Z0-9]", "");
    }

    private boolean skipValidation(CreateRegistrationRequest createRegistrationRequest) {
        if (isNull(createRegistrationRequest)) {
            return true;
        }

        return isBlank(createRegistrationRequest.getCountry()) || isBlank(createRegistrationRequest.getPhone());
    }


}
