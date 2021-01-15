package com.isilona.registry.shared.mapping;

import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public static RegistrationMapper registrationMapper = new RegistrationMapperImpl();
}
