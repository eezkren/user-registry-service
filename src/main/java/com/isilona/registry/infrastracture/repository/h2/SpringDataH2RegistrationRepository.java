package com.isilona.registry.infrastracture.repository.h2;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataH2RegistrationRepository extends JpaRepository<RegistrationEntity, UUID> {

    boolean existsRegistrationEntityByEmail(String email);
}
