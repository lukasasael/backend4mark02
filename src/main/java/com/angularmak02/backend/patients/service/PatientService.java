package com.angularmak02.backend.patients.service;

import com.angularmak02.backend.patients.dto.PatientRequest;
import com.angularmak02.backend.patients.dto.PatientResponse;
import com.angularmak02.backend.patients.entity.Patient;
import com.angularmak02.backend.patients.repository.PatientRepository;
import com.angularmak02.backend.shared.exception.ResourceNotFoundException;
import com.angularmak02.backend.storage.service.S3Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final S3Service s3Service;

    public PatientService(PatientRepository patientRepository, S3Service s3Service) {
        this.patientRepository = patientRepository;
        this.s3Service = s3Service;
    }

    public List<PatientResponse> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PatientResponse findById(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

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
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

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
                patient.getDataInicio(),
                patient.getProfileImageKey());
    }

    public PatientResponse uploadPhoto(
            UUID id,
            MultipartFile file) throws java.io.IOException {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        String key = s3Service.upload(file);

        patient.setProfileImageKey(key);

        Patient savedPatient = patientRepository.save(patient);

        return toResponse(savedPatient);
    }
}