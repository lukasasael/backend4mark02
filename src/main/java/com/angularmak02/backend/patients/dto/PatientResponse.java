package com.angularmak02.backend.patients.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PatientResponse(
    UUID id,
    String nome,
    Integer idade,
    String planoTratamento,
    String historico,
    LocalDate dataInicio
) {}