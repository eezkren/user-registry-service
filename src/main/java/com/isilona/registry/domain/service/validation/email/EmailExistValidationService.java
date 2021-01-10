package com.isilona.registry.domain.service.validation.email;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailExistValidationService {

    @Value("${validation.email.existing}")
    private final List<String> existingEmails;

    public EmailExistValidationService(@Value("${validation.email.existing}") List<String> existingEmails) {
        this.existingEmails = existingEmails;
    }


    public boolean isValid(String email) {
        if (isBlank(email)) {
            return true;
        }
        return existingEmails.stream().noneMatch(email::equalsIgnoreCase);
    }
}
