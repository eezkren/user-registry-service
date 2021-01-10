package com.isilona.registry.domain.service.validation.country;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.isilona.registry.domain.service.validation.country.AllowedCountryValidationService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AllowedCountryValidationServiceTest {

    private static final List<String> ALLOWED_COUNTRIES = List.of("ES");
    private final AllowedCountryValidationService validator = new AllowedCountryValidationService(ALLOWED_COUNTRIES);

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
        String randomAllowedCountry = ALLOWED_COUNTRIES.get(SecureRandom.getInstanceStrong().nextInt(ALLOWED_COUNTRIES.size()));
        assertTrue(validator.isValid(randomAllowedCountry));
    }

    @Test
    void testAnyNotInListIsInvalid() {
        assertFalse(validator.isValid("XX"));
    }


}