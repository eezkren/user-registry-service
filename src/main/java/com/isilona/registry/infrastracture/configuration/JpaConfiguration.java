package com.isilona.registry.infrastracture.configuration;

import com.isilona.registry.infrastracture.repository.h2.SpringDataH2RegistrationRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataH2RegistrationRepository.class)
public class JpaConfiguration {

}
