package com.isilona.registry.domain.service.validation.email;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.isilona.registry.domain.service.validation.email.EmailExistValidationService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import org.junit.jupiter.api.Test;

class EmailExistValidationServiceTest {

    private static final List<String> EXISTING_EMAILS = List.of("registered@gmail.com");
    private final EmailExistValidationService validator = new EmailExistValidationService(EXISTING_EMAILS);

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
    void testAnyFromListIsNotValid() throws NoSuchAlgorithmException {
        String randomExistingEmail = EXISTING_EMAILS.get(SecureRandom.getInstanceStrong().nextInt(EXISTING_EMAILS.size()));
        assertFalse(validator.isValid(randomExistingEmail));
        assertFalse(validator.isValid(randomExistingEmail.toUpperCase()));
    }

    @Test
    void testAnyNotInListIsValid() {
        assertTrue(validator.isValid("XX"));
    }

}