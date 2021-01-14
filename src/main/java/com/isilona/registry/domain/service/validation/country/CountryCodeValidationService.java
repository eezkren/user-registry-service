package com.isilona.registry.domain.service.validation.country;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.isilona.registry.domain.service.validation.ValidationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;

@Service
public class CountryCodeValidationService implements ValidationService<String> {

    public boolean isValid(String countryCode) {
        if (isBlank(countryCode)) {
            return true;
        }

        return Arrays.asList(Locale.getISOCountries()).contains(countryCode.toUpperCase());
    }
}
