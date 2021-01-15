package com.isilona.registry.domain.port.repository;

import com.isilona.registry.domain.model.Registration;
import java.util.UUID;

public interface RegistrationRepository {

    UUID create(Registration domainObject);

    boolean emailNotExists(String email);

}
