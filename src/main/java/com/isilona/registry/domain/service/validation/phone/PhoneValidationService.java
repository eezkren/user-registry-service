package com.isilona.registry.domain.service.validation.phone;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.isilona.registry.application.request.CreateRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class PhoneValidationService {

    private final PhoneNumberValidationStrategyFactory phoneNumberValidationStrategyFactory;

    public PhoneValidationService(
        PhoneNumberValidationStrategyFactory phoneNumberValidationStrategyFactory) {
        this.phoneNumberValidationStrategyFactory = phoneNumberValidationStrategyFactory;
    }


    public boolean isValid(CreateRegistrationRequest createRegistrationRequest) {
        if (skipValidation(createRegistrationRequest)) {
            return true;
        }

        String formattedPhoneNumber = format(createRegistrationRequest.getPhone());
        String country = createRegistrationRequest.getCountry();

        PhoneNumberValidationStrategy phoneNumberValidationStrategy = phoneNumberValidationStrategyFactory.getPhoneNumberValidationStrategy(country);
        return phoneNumberValidationStrategy.isValid(formattedPhoneNumber);
    }

    private String format(String input) {
        return "+" + input.replaceAll("[^a-zA-Z0-9]", "");
    }

    private boolean skipValidation(CreateRegistrationRequest createRegistrationRequest) {
        if (isNull(createRegistrationRequest)) {
            return true;
        }

        return isBlank(createRegistrationRequest.getCountry()) || isBlank(createRegistrationRequest.getPhone());
    }
}
