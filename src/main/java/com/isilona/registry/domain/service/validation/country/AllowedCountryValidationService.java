package com.isilona.registry.domain.service.validation.country;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AllowedCountryValidationService {

    @Value("${validation.country.allowed}")
    private final List<String> allowedCountries;

    public AllowedCountryValidationService(@Value("${validation.country.allowed}") List<String> allowedCountries) {
        this.allowedCountries = allowedCountries;
    }

    public boolean isValid(String countryCodeField) {
        if (isBlank(countryCodeField)) {
            return true;
        }

        return allowedCountries.contains(countryCodeField.toUpperCase());
    }
}
