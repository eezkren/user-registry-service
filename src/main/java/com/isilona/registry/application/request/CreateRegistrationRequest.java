package com.isilona.registry.application.request;

import static com.isilona.registry.application.ValidationConstants.EMAIL;

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

    @Size(min = 2, max = 2)
    @NotBlank
    private String country;

}
