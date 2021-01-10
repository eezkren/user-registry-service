package com.isilona.registry.domain.service.validation.country;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.isilona.registry.domain.service.validation.country.CountryCodeValidationService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CountryCodeValidationServiceTest {

    private final CountryCodeValidationService validator = new CountryCodeValidationService();


    @Test
    void testNullIsValid() {
        assertTrue(validator.isValid(null));
    }

    @Test
    void testEmptyIsValid() {
        assertTrue(validator.isValid(""));
        assertTrue(validator.isValid(" "));
        assertTrue(validator.isValid("       "));
    }

    @Test
    void testAnyFromListIsValid() throws NoSuchAlgorithmException {
        String randomAllowedCountry = Locale.getISOCountries()[SecureRandom.getInstanceStrong().nextInt(Locale.getISOCountries().length)];
        assertTrue(validator.isValid(randomAllowedCountry));
    }

    @Test
    void testAnyNotInListIsInvalid() {
        assertFalse(validator.isValid("XX"));
    }

}