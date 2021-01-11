package com.isilona.registry.infrastracture.configuration;

import com.isilona.registry.RegistryApplication;
import com.isilona.registry.domain.mapping.RegistrationMapper;
import com.isilona.registry.domain.repository.RegistrationRepository;
import com.isilona.registry.domain.service.DomainRegistrationService;
import com.isilona.registry.domain.service.RegistrationService;
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

}
