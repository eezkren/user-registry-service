package com.isilona.registry.infrastracture.repository.h2;

import com.isilona.registry.domain.mapping.RegistrationMapper;
import com.isilona.registry.domain.model.Registration;
import com.isilona.registry.domain.repository.RegistrationRepository;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class H2DbRegistrationRepository implements RegistrationRepository {

    private final SpringDataH2RegistrationRepository springDataRepository;
    private final RegistrationMapper mapper;

    public H2DbRegistrationRepository(SpringDataH2RegistrationRepository registrationRepository, RegistrationMapper mapper) {
        this.springDataRepository = registrationRepository;
        this.mapper = mapper;
    }

    @Override
    public UUID create(Registration domainObject) {
        return springDataRepository.save(mapper.domainToEntityObject(domainObject)).getId();
    }

    @Override
    public boolean emailNotExists(String email) {
        return !springDataRepository.existsRegistrationEntityByEmail(email);
    }
}
