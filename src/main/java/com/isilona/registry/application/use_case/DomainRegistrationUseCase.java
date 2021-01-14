package com.isilona.registry.application.use_case;

import com.isilona.registry.infrastracture.rest.request.CreateRegistrationRequest;
import com.isilona.registry.domain.mapping.RegistrationMapper;
import com.isilona.registry.domain.model.Registration;
import com.isilona.registry.domain.repository.RegistrationRepository;
import com.isilona.registry.domain.service.notification.RegistrationCreatedProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomainRegistrationUseCase implements RegistrationUseCase {

    private final RegistrationMapper mapper;
    private final RegistrationRepository repository;
    private final RegistrationCreatedProducer producer;

    @Override
    public UUID createRegistration(CreateRegistrationRequest requestObject) {

        final Registration registration = mapper.requestToDomainObject(requestObject);

        UUID createdRegistrationId = repository.create(registration);
        registration.setId(createdRegistrationId);

        producer.notify(registration);

        return createdRegistrationId;
    }

}
