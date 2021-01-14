package com.isilona.registry.infrastracture.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
}
