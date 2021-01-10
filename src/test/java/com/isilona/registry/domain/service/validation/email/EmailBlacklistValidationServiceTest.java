package com.isilona.registry.domain.service.validation.email;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.isilona.registry.domain.service.validation.email.EmailBlacklistValidationService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import org.junit.jupiter.api.Test;

class EmailBlacklistValidationServiceTest {

    private static final List<String> BLACKLISTED_EMAILS = List.of("user986@gmail.com", "evil@gmail.com", "chuck_norris@gmail.com");
    private final EmailBlacklistValidationService validator = new EmailBlacklistValidationService(BLACKLISTED_EMAILS);

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
        String randomBlacklistedEmail = BLACKLISTED_EMAILS.get(SecureRandom.getInstanceStrong().nextInt(BLACKLISTED_EMAILS.size()));
        assertFalse(validator.isValid(randomBlacklistedEmail));
        assertFalse(validator.isValid(randomBlacklistedEmail.toUpperCase()));
    }

    @Test
    void testAnyNotInListIsValid() {
        assertTrue(validator.isValid("XX"));
    }

}