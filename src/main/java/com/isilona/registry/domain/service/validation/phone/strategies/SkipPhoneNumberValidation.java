package com.isilona.registry.domain.service.validation.phone.strategies;

import com.isilona.registry.domain.service.validation.phone.PhoneNumberValidationStrategy;

public class SkipPhoneNumberValidation implements PhoneNumberValidationStrategy {

    @Override
    public boolean isValid(String phoneNumber) {
        return true;
    }
}
