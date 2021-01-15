package com.isilona.registry.infrastracture.adapter.repository;

import com.isilona.registry.domain.model.Registration;
import com.isilona.registry.domain.port.repository.RegistrationRepository;
import java.util.UUID;

import com.isilona.registry.infrastracture.model.RegistrationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class H2DbRegistrationRepository implements RegistrationRepository {

    private final SpringDataH2RegistrationRepository springDataRepository;

    @Override
    public UUID create(Registration registration) {

        return springDataRepository.save(RegistrationEntity.from(registration)).getId();
    }

    @Override
    public boolean emailNotExists(String email) {
        return !springDataRepository.existsRegistrationEntityByEmail(email);
    }
}
