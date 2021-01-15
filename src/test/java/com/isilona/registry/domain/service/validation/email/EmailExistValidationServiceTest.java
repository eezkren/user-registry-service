package com.isilona.registry.domain.service.validation.email;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import com.isilona.registry.domain.port.repository.RegistrationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmailExistValidationServiceTest {

    private static final List<String> EXISTING_EMAILS = List.of("registered@gmail.com");

    @Mock
    private RegistrationRepository registrationRepository;

    @InjectMocks
    private EmailExistValidationService validator;


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
        Mockito.when(registrationRepository.emailNotExists(randomExistingEmail)).thenReturn(false);
        assertFalse(validator.isValid(randomExistingEmail));
        assertFalse(validator.isValid(randomExistingEmail.toUpperCase()));
    }

    @Test
    void testAnyNotInListIsValid() {
        Mockito.when(registrationRepository.emailNotExists("XX")).thenReturn(true);
        assertTrue(validator.isValid("XX"));
    }

}