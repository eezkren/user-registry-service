package com.isilona.registry.domain.service.validation.email;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.isilona.registry.domain.service.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class EmailExistValidationService {

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
