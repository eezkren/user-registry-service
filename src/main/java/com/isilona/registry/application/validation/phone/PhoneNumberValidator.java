package com.isilona.registry.application.validation.phone;

import com.isilona.registry.application.request.CreateRegistrationRequest;
import com.isilona.registry.domain.service.validation.phone.PhoneValidationService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, CreateRegistrationRequest> {

    private final PhoneValidationService validatorService;

    public PhoneNumberValidator(PhoneValidationService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(CreateRegistrationRequest createRegistrationRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = validatorService.isValid(createRegistrationRequest);
        if (!isValid) {
            ((ConstraintValidatorContextImpl) constraintValidatorContext)
                .addMessageParameter("yourCountry", createRegistrationRequest.getCountry().toUpperCase());
        }
        return isValid;
    }
}
