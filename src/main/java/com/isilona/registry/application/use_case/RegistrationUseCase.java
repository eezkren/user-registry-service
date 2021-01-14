package com.isilona.registry.application.use_case;

import com.isilona.registry.infrastracture.rest.request.CreateRegistrationRequest;
import java.util.UUID;

public interface RegistrationUseCase {

    UUID createRegistration(CreateRegistrationRequest requestObject);

}
