package com.isilona.registry.application.rest;

import com.isilona.registry.application.request.CreateRegistrationRequest;
import com.isilona.registry.domain.service.RegistrationService;
import java.util.UUID;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog
@RestController
public class RegistrationControllerImpl implements RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationControllerImpl(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public ResponseEntity<UUID> createRegistration(CreateRegistrationRequest createRegistrationRequest) {
        log.info(createRegistrationRequest);
        return ResponseEntity.ok()
            .body(registrationService.createRegistration(createRegistrationRequest));
    }
}
