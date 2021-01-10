package com.isilona.registry.domain.service.validation.email;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailBlacklistValidationService {

    @Value("${validation.email.blacklisted}")
    private final List<String> blacklistedEmails;

    public EmailBlacklistValidationService(@Value("${validation.email.blacklisted}") List<String> blacklistedEmails) {
        this.blacklistedEmails = blacklistedEmails;
    }

    public boolean isValid(String email) {
        if (isBlank(email)) {
            return true;
        }
        return blacklistedEmails.stream().noneMatch(email::equalsIgnoreCase);
    }
}
