package com.isilona.registry.infrastracture.rest.request;

import com.isilona.registry.application.validation.country.AllowedCountryConstraint;
import com.isilona.registry.application.validation.country.CountryCodeConstraint;
import com.isilona.registry.application.validation.email.EmailBlacklistConstraint;
import com.isilona.registry.application.validation.email.EmailExistConstraint;
import com.isilona.registry.application.validation.phone.PhoneNumberConstraint;
import com.isilona.registry.shared.mapping.Mapper;
import com.isilona.registry.domain.model.Registration;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

import static com.isilona.registry.application.validation.ValidationConstants.EMAIL;

@Getter
@Setter
@ToString
@Builder
@PhoneNumberConstraint
public class CreateRegistrationRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @EmailBlacklistConstraint
    @EmailExistConstraint
    @Email(regexp = EMAIL)
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @AllowedCountryConstraint
    @CountryCodeConstraint
    @Size(min = 2, max = 2, message = "{com.isilona.registry.validation.CountryCode.size.message}")
    @NotBlank
    private String country;

    public Registration toRegistration() {
        Registration registration = Mapper.registrationMapper.requestToDomainObject(this);
        registration.setId(UUID.randomUUID());
        return registration;
    }

}
