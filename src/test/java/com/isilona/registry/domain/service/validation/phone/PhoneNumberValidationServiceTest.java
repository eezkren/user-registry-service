package com.isilona.registry.domain.service.validation.phone;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import com.isilona.registry.infrastracture.rest.request.CreateRegistrationRequest;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class PhoneNumberValidationServiceTest {

    private static final Faker faker = new Faker(new Locale("ES"), new RandomService());

    private final PhoneNumberValidationStrategyFactory phoneNumberValidationStrategyFactory = new PhoneNumberValidationStrategyFactory();
    private final PhoneValidationService validator = new PhoneValidationService(phoneNumberValidationStrategyFactory);

    @Test
    void testNullIsValid() {
        assertTrue(validator.isValid(null));

        CreateRegistrationRequest request = CreateRegistrationRequest.builder()
            .country(null)
            .phone(faker.phoneNumber().cellPhone())
            .build();

        assertTrue(validator.isValid(request));

        request = CreateRegistrationRequest.builder()
            .country(faker.country().countryCode2())
            .phone(null)
            .build();

        assertTrue(validator.isValid(request));
    }

    @Test
    void testEmptyIsValid() {
        CreateRegistrationRequest request = CreateRegistrationRequest.builder()
            .country("")
            .phone(faker.phoneNumber().cellPhone())
            .build();
        assertTrue(validator.isValid(request));

        request = CreateRegistrationRequest.builder()
            .country(" ")
            .phone(faker.phoneNumber().cellPhone())
            .build();
        assertTrue(validator.isValid(request));

        request = CreateRegistrationRequest.builder()
            .country("       ")
            .phone(faker.phoneNumber().cellPhone())
            .build();
        assertTrue(validator.isValid(request));

        request = CreateRegistrationRequest.builder()
            .country(faker.country().countryCode2())
            .phone("")
            .build();
        assertTrue(validator.isValid(request));

        request = CreateRegistrationRequest.builder()
            .country(faker.country().countryCode2())
            .phone("  ")
            .build();
        assertTrue(validator.isValid(request));

        request = CreateRegistrationRequest.builder()
            .country(faker.country().countryCode2())
            .phone("       ")
            .build();
        assertTrue(validator.isValid(request));
    }

    @Test
    void testEsPhoneNumbers() {

        CreateRegistrationRequest request = CreateRegistrationRequest.builder()
            .country("ES")
            .phone("+34678666999")
            .build();

        assertTrue(validator.isValid(request));

        request = CreateRegistrationRequest.builder()
            .country("ES")
            .phone("+346786669990")
            .build();

        assertFalse(validator.isValid(request));

        request = CreateRegistrationRequest.builder()
            .country("es")
            .phone("+34678666999")
            .build();

        assertTrue(validator.isValid(request));

        request = CreateRegistrationRequest.builder()
            .country("es")
            .phone("+346786669990")
            .build();

        assertFalse(validator.isValid(request));

        request = CreateRegistrationRequest.builder()
            .country("es")
            .phone("+346-78-66-69-99")
            .build();

        assertTrue(validator.isValid(request));

    }
}