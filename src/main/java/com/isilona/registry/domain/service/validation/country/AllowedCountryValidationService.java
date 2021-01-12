package com.isilona.registry.domain.service.validation.country;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.isilona.registry.domain.service.validation.ValidationService;
import java.util.List;

public class AllowedCountryValidationService implements ValidationService<String> {

    private final List<String> allowedCountries;

    public AllowedCountryValidationService(List<String> allowedCountries) {
        this.allowedCountries = allowedCountries;
    }

    @Override
    public boolean isValid(String countryCodeField) {
        if (isBlank(countryCodeField)) {
            return true;
        }

        return allowedCountries.contains(countryCodeField.toUpperCase());
    }
}
