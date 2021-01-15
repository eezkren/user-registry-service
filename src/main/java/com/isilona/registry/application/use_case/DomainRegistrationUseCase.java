package com.isilona.registry.application.use_case;

import com.isilona.registry.domain.event.UserRegisteredEvent;
import com.isilona.registry.domain.model.Registration;
import com.isilona.registry.domain.port.event.NotifierPort;
import com.isilona.registry.domain.port.repository.RegistrationRepository;
import com.isilona.registry.infrastracture.rest.request.CreateRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomainRegistrationUseCase implements RegistrationUseCase {

    private final RegistrationRepository repository;
    private final NotifierPort           producer;

    @Override
    public void createRegistration(CreateRegistrationRequest request) {

        final Registration registration = request.toRegistration();

        repository.create(registration);

        producer.notify(UserRegisteredEvent.of(registration));

    }

}
