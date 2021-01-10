package com.isilona.registry.domain.service.validation.phone.strategies;

import com.isilona.registry.domain.service.validation.phone.PhoneNumberValidationStrategy;

public class EsPhoneNumberValidation implements PhoneNumberValidationStrategy {

    @Override
    public boolean isValid(String phoneNumber) {
        return phoneNumber.length() == 12 && phoneNumber.startsWith("+34");
    }
}
