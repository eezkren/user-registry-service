package com.isilona.registry.application.validation.email;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class EmailExistValidator implements ConstraintValidator<EmailExistConstraint, String> {

    private final List<String> existingEmails;

    public EmailExistValidator(@Value("${validation.email.existing}") List<String> existingEmails) {
        this.existingEmails = existingEmails;
    }

    @Override
    public void initialize(EmailExistConstraint email) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        if (isBlank(email)) {
            return true;
        }
        return existingEmails.stream().noneMatch(email::equalsIgnoreCase);
    }

}
