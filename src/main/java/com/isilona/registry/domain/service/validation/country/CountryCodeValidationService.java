package com.isilona.registry.domain.service.validation.country;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Arrays;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class CountryCodeValidationService {

    public boolean isValid(String countryCode) {
        if (isBlank(countryCode)) {
            return true;
        }

        return Arrays.asList(Locale.getISOCountries()).contains(countryCode.toUpperCase());
    }
}
