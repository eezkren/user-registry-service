package com.isilona.registry.infrastracture.repository.h2;

import com.isilona.registry.domain.mapping.RegistrationMapper;
import com.isilona.registry.domain.model.Registration;
import com.isilona.registry.domain.repository.RegistrationRepository;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class H2DbRegistrationRepository implements RegistrationRepository {

    private final SpringDataH2RegistrationRepository registrationRepository;
    private final RegistrationMapper mapper;

    public H2DbRegistrationRepository(SpringDataH2RegistrationRepository registrationRepository, RegistrationMapper mapper) {
        this.registrationRepository = registrationRepository;
        this.mapper = mapper;
    }

    @Override
    public UUID create(Registration domainObject) {
        return registrationRepository.save(mapper.domainToEntityObject(domainObject)).getId();
    }
}
