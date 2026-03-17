package com.angularmak02.backend.patients.entity;

import java.time.LocalDate;
import java.util.UUID;

public record Patient(
    UUID id,
    String nome,
    Integer idade,
    String planoTratamento,
    String historico,
    LocalDate dataInicio
) {}