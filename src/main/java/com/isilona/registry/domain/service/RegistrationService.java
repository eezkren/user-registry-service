package com.isilona.registry.domain.service;

import com.isilona.registry.application.request.CreateRegistrationRequest;
import java.util.UUID;

public interface RegistrationService {

    UUID createRegistration(CreateRegistrationRequest requestObject);

    boolean emailNotExists(String email);

}
