package com.isilona.registry.domain.mapping;

import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public static RegistrationMapper registrationMapper = new RegistrationMapperImpl();
}
