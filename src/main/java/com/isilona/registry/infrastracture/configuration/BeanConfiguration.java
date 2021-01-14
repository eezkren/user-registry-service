package com.isilona.registry.infrastracture.configuration;

import com.isilona.registry.domain.service.validation.country.AllowedCountryValidationService;
import com.isilona.registry.domain.service.validation.email.EmailBlacklistValidationService;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    AllowedCountryValidationService allowedCountryValidationService(
        final @Value("${validation.country.allowed}") List<String> allowedCountries) {
        return new AllowedCountryValidationService(allowedCountries);
    }

    @Bean
    EmailBlacklistValidationService emailBlacklistValidationService(
        final @Value("${validation.email.blacklisted}") List<String> blacklistedEmails) {
        return new EmailBlacklistValidationService(blacklistedEmails);
    }


}
