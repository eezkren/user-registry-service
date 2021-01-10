package com.isilona.registry.domain.service.validation.phone;

public interface PhoneNumberValidationStrategy {

    boolean isValid(String phoneNumber);
}
