package com.isilona.registry.application.validation.phone;

public class EsPhoneNumberValidation implements PhoneNumberValidationStrategy {

    @Override
    public boolean isValid(String phoneNumber) {
        if (phoneNumber.length() != 12) {
            return false;
        }

        if (!phoneNumber.startsWith("+34")) {
            return false;
        }

        return true;
    }
}
