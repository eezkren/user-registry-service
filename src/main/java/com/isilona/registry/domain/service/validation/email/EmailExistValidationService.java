package com.isilona.registry.domain.service.validation.email;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.isilona.registry.domain.service.RegistrationService;
import com.isilona.registry.domain.service.validation.ValidationService;

public class EmailExistValidationService implements ValidationService<String> {

    private final RegistrationService registrationService;

    public EmailExistValidationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public boolean isValid(String email) {
        if (isBlank(email)) {
            return true;
        }
        return registrationService.emailNotExists(email);
    }
}
