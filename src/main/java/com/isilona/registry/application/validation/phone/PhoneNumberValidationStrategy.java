package com.isilona.registry.application.validation.phone;

public interface PhoneNumberValidationStrategy {

    boolean isValid(String phoneNumber);
}
