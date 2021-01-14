package com.isilona.registry.domain.service.validation.phone;

import com.isilona.registry.domain.service.validation.phone.strategies.EsPhoneNumberValidation;
import com.isilona.registry.domain.service.validation.phone.strategies.SkipPhoneNumberValidation;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Supplier;

@Service
public class PhoneNumberValidationStrategyFactory {

    public PhoneNumberValidationStrategy getPhoneNumberValidationStrategy(String countryCode) {

        Map<String, Supplier<PhoneNumberValidationStrategy>> validations = Map.of(
                "ES", EsPhoneNumberValidation::new
                                                                                 );
        return validations.getOrDefault(countryCode.toUpperCase(), SkipPhoneNumberValidation::new).get();

    }
}
