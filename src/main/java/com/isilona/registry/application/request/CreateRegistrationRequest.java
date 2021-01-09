package com.isilona.registry.application.request;

import static com.isilona.registry.application.validation.ValidationConstants.EMAIL;

import com.isilona.registry.application.validation.allowedcountry.AllowedCountryConstraint;
import com.isilona.registry.application.validation.countryocde.CountryCodeConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class CreateRegistrationRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

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

}
