package com.isilona.registry.application.rest;

import com.isilona.registry.application.request.CreateRegistrationRequest;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/registration")
public interface RegistrationController {

    @PostMapping
    ResponseEntity<UUID> createRegistration(
        @Valid @RequestBody final CreateRegistrationRequest createOrderRequest
    );

}
