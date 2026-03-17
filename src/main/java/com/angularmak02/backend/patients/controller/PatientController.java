package com.angularmak02.backend.patients.controller;

import com.angularmak02.backend.patients.dto.PatientRequest;
import com.angularmak02.backend.patients.dto.PatientResponse;
import com.angularmak02.backend.patients.service.PatientService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientResponse> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public PatientResponse findById(@PathVariable UUID id) {
        return patientService.findById(id);
    }

    @PostMapping
    public PatientResponse create(@Valid @RequestBody PatientRequest request) {
        return patientService.save(request);
    }

    @PutMapping("/{id}")
    public PatientResponse update(@PathVariable UUID id, @Valid @RequestBody PatientRequest request) {
        return patientService.update(id, request);
    }
}