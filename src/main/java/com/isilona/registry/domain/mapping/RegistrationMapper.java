package com.isilona.registry.domain.mapping;

import com.isilona.registry.application.request.CreateRegistrationRequest;
import com.isilona.registry.domain.model.Registration;
import com.isilona.registry.infrastracture.repository.h2.RegistrationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    Registration requestToDomainObject(CreateRegistrationRequest requestObject);

    RegistrationEntity domainToEntityObject(Registration domainObject);

}
