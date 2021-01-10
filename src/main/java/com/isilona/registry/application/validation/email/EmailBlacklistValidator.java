package com.isilona.registry.application.validation.email;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class EmailBlacklistValidator implements ConstraintValidator<EmailBlacklistConstraint, String> {

    private final List<String> blacklistedEmails;

    public EmailBlacklistValidator(@Value("${validation.email.blacklisted}") List<String> blacklistedEmails) {
        this.blacklistedEmails = blacklistedEmails;
    }

    @Override
    public void initialize(EmailBlacklistConstraint email) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        if (isBlank(email)) {
            return true;
        }

        return blacklistedEmails.stream().noneMatch(email::equalsIgnoreCase);
    }

}
