package com.angularmak02.backend.patients.service;

import com.angularmak02.backend.patients.dto.PatientRequest;
import com.angularmak02.backend.patients.dto.PatientResponse;
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

    public List<PatientResponse> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PatientResponse findById(UUID id) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        return toResponse(patient);
    }

    public PatientResponse save(PatientRequest request) {
        Patient patient = new Patient();

        patient.setNome(request.nome());
        patient.setIdade(request.idade());
        patient.setPlanoTratamento(request.planoTratamento());
        patient.setHistorico(request.historico());
        patient.setDataInicio(request.dataInicio());

        return toResponse(patientRepository.save(patient));
    }

    public PatientResponse update(UUID id, PatientRequest request) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow();

        patient.setNome(request.nome());
        patient.setIdade(request.idade());
        patient.setPlanoTratamento(request.planoTratamento());
        patient.setHistorico(request.historico());
        patient.setDataInicio(request.dataInicio());

        return toResponse(patientRepository.save(patient));
    }

    private PatientResponse toResponse(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getNome(),
                patient.getIdade(),
                patient.getPlanoTratamento(),
                patient.getHistorico(),
                patient.getDataInicio());
    }
}