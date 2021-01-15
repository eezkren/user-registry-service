package com.isilona.registry.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserRegisteredEvent implements DomainEvent {

    private UUID userId;

    @Override
    public String getTopic() {
        return "topic";
    }
}
