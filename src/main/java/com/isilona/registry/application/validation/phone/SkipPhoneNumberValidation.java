package com.isilona.registry.application.validation.phone;

public class SkipPhoneNumberValidation implements PhoneNumberValidationStrategy {

    @Override
    public boolean isValid(String phoneNumber) {
        return true;
    }
}
