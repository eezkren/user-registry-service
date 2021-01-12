package com.isilona.registry.domain.service.validation.phone;

import com.isilona.registry.domain.service.validation.phone.strategies.EsPhoneNumberValidation;
import com.isilona.registry.domain.service.validation.phone.strategies.SkipPhoneNumberValidation;

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
