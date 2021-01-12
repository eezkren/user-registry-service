package com.isilona.registry.domain.service.validation.email;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.isilona.registry.domain.service.validation.ValidationService;
import java.util.List;

public class EmailBlacklistValidationService implements ValidationService<String> {

    private final List<String> blacklistedEmails;

    public EmailBlacklistValidationService(List<String> blacklistedEmails) {
        this.blacklistedEmails = blacklistedEmails;
    }

    public boolean isValid(String email) {
        if (isBlank(email)) {
            return true;
        }
        return blacklistedEmails.stream().noneMatch(email::equalsIgnoreCase);
    }
}
