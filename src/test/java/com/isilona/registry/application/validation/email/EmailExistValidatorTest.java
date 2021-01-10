package com.isilona.registry.application.validation.email;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class EmailExistValidatorTest {

    private static final List<String> EXISTING_EMAILS = List.of("registered@gmail.com");
    private final EmailBlacklistValidator validator = new EmailBlacklistValidator(EXISTING_EMAILS);

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
    void testAnyFromListIsNotValid() throws NoSuchAlgorithmException {
        String randomExistingEmail = EXISTING_EMAILS.get(SecureRandom.getInstanceStrong().nextInt(EXISTING_EMAILS.size()));
        assertFalse(validator.isValid(randomExistingEmail, cxt));
        assertFalse(validator.isValid(randomExistingEmail.toUpperCase(), cxt));
    }

    @Test
    void testAnyNotInListIsValid() {
        assertTrue(validator.isValid("XX", cxt));
    }

}