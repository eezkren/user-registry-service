package com.isilona.registry.application.validation.email;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class EmailBlacklistValidatorTest {

    private static final List<String> BLACKLISTED_EMAILS = List.of("user986@gmail.com", "evil@gmail.com", "chuck_norris@gmail.com");
    private final EmailBlacklistValidator validator = new EmailBlacklistValidator(BLACKLISTED_EMAILS);

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
    void testAnyFromListIsNotValid() {
        String randomBlacklistedEmail = BLACKLISTED_EMAILS.get(new Random().nextInt(BLACKLISTED_EMAILS.size()));
        assertFalse(validator.isValid(randomBlacklistedEmail, cxt));
        assertFalse(validator.isValid(randomBlacklistedEmail.toUpperCase(), cxt));
    }

    @Test
    void testAnyNotInListIsValid() {
        assertTrue(validator.isValid("XX", cxt));
    }

}