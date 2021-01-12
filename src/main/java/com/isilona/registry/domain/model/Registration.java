package com.isilona.registry.domain.model;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Registration {

    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String country;
}
