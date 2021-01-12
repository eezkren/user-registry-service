package com.isilona.registry.infrastracture.configuration;

import com.isilona.registry.RegistryApplication;
import com.isilona.registry.domain.mapping.RegistrationMapper;
import com.isilona.registry.domain.repository.RegistrationRepository;
import com.isilona.registry.domain.service.DomainRegistrationService;
import com.isilona.registry.domain.service.RegistrationService;
import com.isilona.registry.domain.service.validation.country.AllowedCountryValidationService;
import com.isilona.registry.domain.service.validation.country.CountryCodeValidationService;
import com.isilona.registry.domain.service.validation.email.EmailBlacklistValidationService;
import com.isilona.registry.domain.service.validation.email.EmailExistValidationService;
import com.isilona.registry.domain.service.validation.phone.PhoneNumberValidationStrategyFactory;
import com.isilona.registry.domain.service.validation.phone.PhoneValidationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = RegistryApplication.class)
public class BeanConfiguration {

    @Bean
    RegistrationService registrationService(
        final RegistrationRepository registrationRepository,
        final RegistrationMapper mapper
    ) {
        return new DomainRegistrationService(mapper, registrationRepository);
    }

    @Bean
    AllowedCountryValidationService allowedCountryValidationService(
        final @Value("${validation.country.allowed}") List<String> allowedCountries) {
        return new AllowedCountryValidationService(allowedCountries);
    }

    @Bean
    CountryCodeValidationService countryCodeValidationService() {
        return new CountryCodeValidationService();
    }

    @Bean
    EmailBlacklistValidationService emailBlacklistValidationService(
        final @Value("${validation.email.blacklisted}") List<String> blacklistedEmails) {
        return new EmailBlacklistValidationService(blacklistedEmails);
    }

    @Bean
    EmailExistValidationService emailExistValidationService(
        final RegistrationService registrationService) {
        return new EmailExistValidationService(registrationService);
    }

    @Bean
    PhoneNumberValidationStrategyFactory phoneNumberValidationStrategyFactory() {
        return new PhoneNumberValidationStrategyFactory();
    }

    @Bean
    PhoneValidationService phoneValidationService(
        final PhoneNumberValidationStrategyFactory phoneNumberValidationStrategyFactory) {
        return new PhoneValidationService(phoneNumberValidationStrategyFactory);
    }


}
