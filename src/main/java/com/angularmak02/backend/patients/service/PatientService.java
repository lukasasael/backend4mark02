package com.angularmak02.backend.patients.service;

import com.angularmak02.backend.patients.entity.Patient;
import com.angularmak02.backend.patients.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(UUID id) {
        return patientRepository.findById(id)
                .orElseThrow();
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
}