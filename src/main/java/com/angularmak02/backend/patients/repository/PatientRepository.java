package com.angularmak02.backend.patients.repository;

import com.angularmak02.backend.patients.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
}