package com.isilona.registry.application.validation.email;

import com.isilona.registry.domain.service.validation.email.EmailBlacklistValidationService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailBlacklistValidator implements ConstraintValidator<EmailBlacklistConstraint, String> {

    private final EmailBlacklistValidationService validatorService;

    public EmailBlacklistValidator(EmailBlacklistValidationService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public void initialize(EmailBlacklistConstraint email) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        return validatorService.isValid(email);
    }

}
