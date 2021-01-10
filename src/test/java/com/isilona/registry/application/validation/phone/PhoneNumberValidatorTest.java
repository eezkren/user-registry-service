package com.isilona.registry.application.validation.phone;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import com.isilona.registry.application.request.CreateRegistrationRequest;
import java.util.Locale;
import javax.validation.ConstraintValidator;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PhoneNumberValidatorTest {

    private static final Faker faker = new Faker(new Locale("ES"), new RandomService());
    private final ConstraintValidator<PhoneNumberConstraint, CreateRegistrationRequest> validator = new PhoneNumberValidator();
    @Mock
    ConstraintValidatorContextImpl cxt;


    @Test
    void testNullIsValid() {
        assertTrue(validator.isValid(null, cxt));

        CreateRegistrationRequest request = CreateRegistrationRequest.builder()
            .country(null)
            .phone(faker.phoneNumber().cellPhone())
            .build();

        assertTrue(validator.isValid(request, cxt));

        request = CreateRegistrationRequest.builder()
            .country(faker.country().countryCode2())
            .phone(null)
            .build();

        assertTrue(validator.isValid(request, cxt));
    }

    @Test
    void testEmptyIsValid() {
        CreateRegistrationRequest request = CreateRegistrationRequest.builder()
            .country("")
            .phone(faker.phoneNumber().cellPhone())
            .build();
        assertTrue(validator.isValid(request, cxt));

        request = CreateRegistrationRequest.builder()
            .country(" ")
            .phone(faker.phoneNumber().cellPhone())
            .build();
        assertTrue(validator.isValid(request, cxt));

        request = CreateRegistrationRequest.builder()
            .country("       ")
            .phone(faker.phoneNumber().cellPhone())
            .build();
        assertTrue(validator.isValid(request, cxt));

        request = CreateRegistrationRequest.builder()
            .country(faker.country().countryCode2())
            .phone("")
            .build();
        assertTrue(validator.isValid(request, cxt));

        request = CreateRegistrationRequest.builder()
            .country(faker.country().countryCode2())
            .phone("  ")
            .build();
        assertTrue(validator.isValid(request, cxt));

        request = CreateRegistrationRequest.builder()
            .country(faker.country().countryCode2())
            .phone("       ")
            .build();
        assertTrue(validator.isValid(request, cxt));
    }

    @Test
    void testEsPhoneNumbers() {

        CreateRegistrationRequest request = CreateRegistrationRequest.builder()
            .country("ES")
            .phone("+34678666999")
            .build();

        assertTrue(validator.isValid(request, cxt));

        request = CreateRegistrationRequest.builder()
            .country("ES")
            .phone("+346786669990")
            .build();

        assertFalse(validator.isValid(request, cxt));

        request = CreateRegistrationRequest.builder()
            .country("es")
            .phone("+34678666999")
            .build();

        assertTrue(validator.isValid(request, cxt));

        request = CreateRegistrationRequest.builder()
            .country("es")
            .phone("+346786669990")
            .build();

        assertFalse(validator.isValid(request, cxt));

        request = CreateRegistrationRequest.builder()
            .country("es")
            .phone("+346-78-66-69-99")
            .build();

        assertTrue(validator.isValid(request, cxt));

    }
}