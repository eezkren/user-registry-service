package com.isilona.registry.application.validation.countryocde;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import java.util.Random;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CountryCodeValidatorTest {

    CountryCodeValidator validator = new CountryCodeValidator();

    @Mock
    ConstraintValidatorContextImpl cxt;

    @Test
    void testNullIsValid() {
        assertTrue(validator.isValid(null, cxt));
    }

    @Test
    void testEmptyIsValid() {
        assertTrue(validator.isValid("", cxt));
        assertTrue(validator.isValid(" ", cxt));
        assertTrue(validator.isValid("       ", cxt));
    }

    @Test
    void testAnyFromListIsValid() {
        String randomAllowedCountry = Locale.getISOCountries()[new Random().nextInt(Locale.getISOCountries().length)];
        assertTrue(validator.isValid(randomAllowedCountry, cxt));
    }

    @Test
    void testAnyNotInListIsInvalid() {
        assertFalse(validator.isValid("XX", cxt));
    }

}