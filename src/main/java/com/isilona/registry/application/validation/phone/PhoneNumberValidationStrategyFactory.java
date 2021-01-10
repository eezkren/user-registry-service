package com.isilona.registry.application.validation.phone;

public class PhoneNumberValidationStrategyFactory {

    private final EsPhoneNumberValidation esPhoneNumberValidation = new EsPhoneNumberValidation();
    private final SkipPhoneNumberValidation skipPhoneNumberValidation = new SkipPhoneNumberValidation();

    public PhoneNumberValidationStrategy getPhoneNumberValidationStrategy(String countryCode) {
        switch (countryCode.toUpperCase()) {
            case "ES":
                return esPhoneNumberValidation;
            default:
                return skipPhoneNumberValidation;
        }
    }
}
