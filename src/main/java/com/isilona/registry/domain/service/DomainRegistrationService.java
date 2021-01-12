package com.isilona.registry.domain.service;

import com.isilona.registry.application.request.CreateRegistrationRequest;
import com.isilona.registry.domain.mapping.RegistrationMapper;
import com.isilona.registry.domain.model.Registration;
import com.isilona.registry.domain.repository.RegistrationRepository;
import com.isilona.registry.domain.service.notification.RegistrationCreatedProducer;
import java.util.UUID;

public class DomainRegistrationService implements RegistrationService {

    private final RegistrationMapper mapper;
    private final RegistrationRepository repository;
    private final RegistrationCreatedProducer producer;

    public DomainRegistrationService(
        RegistrationMapper mapper,
        RegistrationRepository repository,
        RegistrationCreatedProducer producer
    ) {
        this.mapper = mapper;
        this.repository = repository;
        this.producer = producer;
    }

    @Override
    public UUID createRegistration(CreateRegistrationRequest requestObject) {
        final Registration registration = mapper.requestToDomainObject(requestObject);

        UUID createdRegistrationId = repository.create(registration);
        registration.setId(createdRegistrationId);
        producer.notify(registration);

        return createdRegistrationId;
    }

    @Override
    public boolean emailNotExists(String email) {
        return repository.emailNotExists(email);
    }
}
