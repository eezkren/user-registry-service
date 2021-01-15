package com.isilona.registry.domain.service.validation.email;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.isilona.registry.domain.port.repository.RegistrationRepository;
import com.isilona.registry.domain.service.validation.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class EmailExistValidationService implements ValidationService<String> {

    private final RegistrationRepository registrationRepository;

    public EmailExistValidationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public boolean isValid(String email) {
        if (isBlank(email)) {
            return true;
        }
        return registrationRepository.emailNotExists(email);
    }
}
