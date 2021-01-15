package com.isilona.registry.infrastracture.model;

import com.isilona.registry.shared.mapping.Mapper;
import com.isilona.registry.domain.model.Registration;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "registrations")
public class RegistrationEntity {

    @Id
    @GeneratedValue
    @Column(name = "pk_id")
    private UUID id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "country_code")
    private String country;

    public static RegistrationEntity from(Registration registration) {
        return Mapper.registrationMapper.domainToEntityObject(registration);
    }
}
