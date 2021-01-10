package com.isilona.registry.application.validation.phone;

public class EsPhoneNumberValidation implements PhoneNumberValidationStrategy {

    @Override
    public boolean isValid(String phoneNumber) {
        return phoneNumber.length() == 12 && phoneNumber.startsWith("+34");
    }
}
