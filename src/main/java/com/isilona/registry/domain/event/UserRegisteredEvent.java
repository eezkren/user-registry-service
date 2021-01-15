package com.isilona.registry.domain.event;

import com.isilona.registry.domain.model.Registration;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.UnknownServiceException;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisteredEvent implements DomainEvent {

    private UUID userId;

    public static UserRegisteredEvent of(Registration registration){
        return new UserRegisteredEvent(registration.getId());
    }
    @Override
    public String getTopic() {
        return "topic";
    }
}
