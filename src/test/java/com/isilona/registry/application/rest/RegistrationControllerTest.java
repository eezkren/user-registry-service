package com.isilona.registry.application.rest;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import com.isilona.registry.application.request.CreateRegistrationRequest;
import com.isilona.registry.domain.service.RegistrationService;
import com.isilona.registry.domain.service.validation.country.AllowedCountryValidationService;
import com.isilona.registry.domain.service.validation.country.CountryCodeValidationService;
import com.isilona.registry.domain.service.validation.email.EmailBlacklistValidationService;
import com.isilona.registry.domain.service.validation.email.EmailExistValidationService;
import com.isilona.registry.domain.service.validation.phone.PhoneNumberValidationStrategyFactory;
import com.isilona.registry.domain.service.validation.phone.PhoneValidationService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    private static final Faker faker = new Faker(new Locale("ES"), new RandomService());
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final Map<String, List<String>> validCountryCodesAndPhoneNumbers = Map.of("ES", List.of("+34678666999", "+34-678-666-999"));

    @Autowired
    protected MockMvc mockMvc;

    @SpyBean
    private AllowedCountryValidationService allowedCountryValidationService;
    @SpyBean
    private CountryCodeValidationService countryCodeValidationService;
    @SpyBean
    private EmailBlacklistValidationService emailBlacklistValidationService;
    @SpyBean
    private EmailExistValidationService emailExistValidationService;
    @SpyBean
    private PhoneValidationService phoneValidationService;
    @SpyBean
    private PhoneNumberValidationStrategyFactory phoneNumberValidationStrategyFactory;

    @MockBean
    private RegistrationService registrationService;

    @Value("${validation.country.allowed}")
    private List<String> allowedCountries;


    @Test
    void postShouldReturnOK() throws Exception {

        CreateRegistrationRequest request = buildCreateRegistrationRequest();

        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void postWithoutNameShouldReturnError() throws Exception {

        CreateRegistrationRequest request = buildCreateRegistrationRequest();
        String expectedErrorMessage = "name: must not be blank";

        request.setName("");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));

        request.setName(null);
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));

        request.setName("  ");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));
    }

    @Test
    void postWithoutSurnameShouldReturnError() throws Exception {

        CreateRegistrationRequest request = buildCreateRegistrationRequest();
        String expectedErrorMessage = "surname: must not be blank";

        request.setSurname("");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));

        request.setSurname(null);
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));

        request.setSurname("  ");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));
    }

    @Test
    void postWithoutPhoneShouldReturnError() throws Exception {

        CreateRegistrationRequest request = buildCreateRegistrationRequest();
        String expectedErrorMessage = "phone: must not be blank";

        request.setPhone("");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));

        request.setPhone(null);
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));

        request.setPhone("  ");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));
    }

    @Test
    void postWithoutCountryShouldReturnError() throws Exception {

        CreateRegistrationRequest request = buildCreateRegistrationRequest();
        String expectedErrorMessage = "country: must not be blank";
        String expectedSizeErrorMessage = "country: {com.isilona.registry.validation.CountryCode.size.message}";

        request.setCountry("");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(2))
            .andExpect(jsonPath("$.errors").value(
                containsInAnyOrder(expectedErrorMessage, expectedSizeErrorMessage)));

        request.setCountry(null);
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));

        request.setCountry("  ");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));
    }

    @Test
    void postWithoutEmailShouldReturnError() throws Exception {

        CreateRegistrationRequest request = buildCreateRegistrationRequest();
        String expectedErrorMessage = "email: must not be blank";
        String expectedFormatErrorMessage = "email: must be a well-formed email address";

        request.setEmail("");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(2))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage, expectedFormatErrorMessage)));

        request.setEmail(null);
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage)));

        request.setEmail("  ");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(2))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedErrorMessage, expectedFormatErrorMessage)));
    }

    @Test
    void postWithInvalidEmailFormatShouldReturnError() throws Exception {
        CreateRegistrationRequest request = buildCreateRegistrationRequest();
        String expectedFormatErrorMessage = "email: must be a well-formed email address";

        request.setEmail("hello@there");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedFormatErrorMessage)));

        request.setEmail("hello@there#com");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedFormatErrorMessage)));

        request.setEmail("hello..there@gmail.com");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedFormatErrorMessage)));
    }

    @Test
    void postWithInvalidCountryFormatShouldReturnError() throws Exception {

        CreateRegistrationRequest request = buildCreateRegistrationRequest();
        String expectedFormatErrorMessage = "country: {com.isilona.registry.validation.CountryCode.size.message}";
        String expectedCountryCodeErrorMessage = "country: {com.isilona.registry.validation.CountryCode.message}";
        String expectedCountryNorAllowedErrorMessage = "country: {com.isilona.registry.validation.AllowedCountry.message}";

        request.setCountry("BUL");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(3))
            .andExpect(jsonPath("$.errors")
                .value(containsInAnyOrder(
                    expectedFormatErrorMessage,
                    expectedCountryCodeErrorMessage,
                    expectedCountryNorAllowedErrorMessage
                )));

        request.setCountry("B");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(3))
            .andExpect(jsonPath("$.errors")
                .value(containsInAnyOrder(
                    expectedFormatErrorMessage,
                    String.format(expectedCountryCodeErrorMessage, request.getCountry()),
                    String.format(expectedCountryNorAllowedErrorMessage, request.getCountry())
                )));

        request.setCountry("ES");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isOk());
    }


    @Test
    void postWithBlacklistedEmailShouldReturnError() throws Exception {
        CreateRegistrationRequest request = buildCreateRegistrationRequest();
        String expectedBlacklistedEmailErrorMessage = "email: {com.isilona.registry.validation.EmailBlacklist.message}";

        request.setEmail("evil@gmail.com");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedBlacklistedEmailErrorMessage)));
    }

    @Test
    void postWithAlreadyExistingEmailShouldReturnError() throws Exception {
        CreateRegistrationRequest request = buildCreateRegistrationRequest();
        String expectedExistingEmailErrorMessage = "email: {com.isilona.registry.validation.EmailExist.message}";

        request.setEmail("registered@gmail.com");
        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getAsJson(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors.length()").value(1))
            .andExpect(jsonPath("$.errors").value(containsInAnyOrder(expectedExistingEmailErrorMessage)));
    }


    private CreateRegistrationRequest buildCreateRegistrationRequest() throws NoSuchAlgorithmException {

        String randomAllowedCountry = allowedCountries.get(SecureRandom.getInstanceStrong().nextInt(allowedCountries.size()));
        List<String> allowedPhoneNumbers = validCountryCodesAndPhoneNumbers.get(randomAllowedCountry);
        String randomValidNumberFromAllowedCountry = allowedPhoneNumbers.get(SecureRandom.getInstanceStrong().nextInt(allowedPhoneNumbers.size()));

        return CreateRegistrationRequest.builder()
            .name(faker.name().lastName())
            .surname(faker.name().lastName())
            .phone(randomValidNumberFromAllowedCountry)
            .country(randomAllowedCountry.toUpperCase())
            .email(faker.internet().emailAddress())
            .build();
    }

    private String getAsJson(Object data) throws JsonProcessingException {
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(data);
    }

}