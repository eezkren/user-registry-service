package com.isilona.registry.application.rest;

import com.isilona.registry.application.request.CreateRegistrationRequest;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog
@RestController
public class RegistrationControllerImpl implements RegistrationController {

    @Override
    public ResponseEntity<CreateRegistrationRequest> createRegistration(CreateRegistrationRequest createRegistrationRequest) {
        log.info(createRegistrationRequest);
        return ResponseEntity.ok()
            .body(createRegistrationRequest);
    }
}
