package com.isilona.registry.domain.repository;

import com.isilona.registry.domain.model.Registration;
import java.util.UUID;

public interface RegistrationRepository {

    UUID create(Registration domainObject);

}
