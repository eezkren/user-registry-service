package com.isilona.registry.infrastracture.rest;

import com.isilona.registry.infrastracture.rest.request.CreateRegistrationRequest;
import com.isilona.registry.application.use_case.RegistrationUseCase;
import java.util.UUID;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog
@RestController
public class RegistrationControllerImpl implements RegistrationController {

    private final RegistrationUseCase registrationUseCase;

    public RegistrationControllerImpl(RegistrationUseCase registrationUseCase) {
        this.registrationUseCase = registrationUseCase;
    }

    @Override
    public ResponseEntity<UUID> createRegistration(CreateRegistrationRequest createRegistrationRequest) {
        log.info(createRegistrationRequest);
        return ResponseEntity.ok()
            .body(registrationUseCase.createRegistration(createRegistrationRequest));
    }
}
