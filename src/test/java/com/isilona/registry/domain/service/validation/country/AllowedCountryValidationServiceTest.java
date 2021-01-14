package com.isilona.registry.domain.service.validation.country;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Stream;

class AllowedCountryValidationServiceTest {

    private static final List<String>                    ALLOWED_COUNTRIES = List.of("ES");
    private final        AllowedCountryValidationService validator         = new AllowedCountryValidationService(ALLOWED_COUNTRIES);

    private static Stream<Arguments> provideCountries() throws NoSuchAlgorithmException {

        String country = ALLOWED_COUNTRIES.get(SecureRandom.getInstanceStrong().nextInt(ALLOWED_COUNTRIES.size()));
        return Stream.of(
                Arguments.of("", true),
                Arguments.of(" ", true),
                Arguments.of("     ", true),
                Arguments.of(country, true),
                Arguments.of(country.toLowerCase(), true),
                Arguments.of(country.toUpperCase(), true),
                Arguments.of("XX", false)
                        );
    }

    @ParameterizedTest
    @MethodSource("provideCountries")
    void testNullIsValid(String country, boolean isValidExpected) {
        boolean isValidRetrieved = validator.isValid(country);
        Assertions.assertThat(isValidExpected).isEqualTo(isValidRetrieved);
    }

}