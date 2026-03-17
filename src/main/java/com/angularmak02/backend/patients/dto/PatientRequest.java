package com.angularmak02.backend.patients.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientRequest(
    @NotBlank String nome,
    @NotNull Integer idade,
    @NotBlank String planoTratamento,
    String historico,
    @NotNull LocalDate dataInicio
) {}