package com.isilona.registry.infrastracture.repository;

import java.util.UUID;

import com.isilona.registry.infrastracture.model.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataH2RegistrationRepository extends JpaRepository<RegistrationEntity, UUID> {

    boolean existsRegistrationEntityByEmail(String email);
}
