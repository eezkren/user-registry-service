package com.isilona.registry.domain.service;

import com.isilona.registry.application.request.CreateRegistrationRequest;
import com.isilona.registry.domain.mapping.RegistrationMapper;
import com.isilona.registry.domain.model.Registration;
import com.isilona.registry.domain.repository.RegistrationRepository;
import java.util.UUID;

public class DomainRegistrationService implements RegistrationService {

    private final RegistrationMapper mapper;
    private final RegistrationRepository repository;

    public DomainRegistrationService(RegistrationMapper mapper, RegistrationRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public UUID createRegistration(CreateRegistrationRequest requestObject) {
        final Registration registration = mapper.requestToDomainObject(requestObject);
        return repository.create(registration);
    }

    @Override
    public boolean emailNotExists(String email) {
        return repository.emailNotExists(email);
    }
}
