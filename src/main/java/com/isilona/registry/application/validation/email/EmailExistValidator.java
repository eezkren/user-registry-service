package com.isilona.registry.application.validation.email;

import com.isilona.registry.domain.service.validation.email.EmailExistValidationService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistValidator implements ConstraintValidator<EmailExistConstraint, String> {

    private final EmailExistValidationService validatorService;

    public EmailExistValidator(EmailExistValidationService validatorService) {
        this.validatorService = validatorService;
    }


    @Override
    public void initialize(EmailExistConstraint email) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        return validatorService.isValid(email);
    }

}
