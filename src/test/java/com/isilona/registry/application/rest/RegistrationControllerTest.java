package com.isilona.registry.application.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import com.isilona.registry.application.request.CreateRegistrationRequest;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    private static final Faker faker = new Faker(new Locale("ES"), new RandomService());

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void greetingShouldReturnMessageFromService() throws Exception {

        CreateRegistrationRequest request = CreateRegistrationRequest.builder()
            .name(faker.name().firstName())
            .surname(faker.name().lastName())
            .phone(faker.phoneNumber().cellPhone())
            .country(faker.country().countryCode2())
            .email(faker.internet().emailAddress())
            .build();

        this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isOk());
    }

}