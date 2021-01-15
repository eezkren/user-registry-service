package com.isilona.registry.shared.mapping;

import com.isilona.registry.infrastracture.rest.request.CreateRegistrationRequest;
import com.isilona.registry.domain.model.Registration;
import com.isilona.registry.infrastracture.model.RegistrationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    Registration requestToDomainObject(CreateRegistrationRequest requestObject);

    RegistrationEntity domainToEntityObject(Registration domainObject);

}
