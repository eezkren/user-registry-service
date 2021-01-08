package com.isilona.registry.application.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class CreateRegistrationRequest {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String country;

}
