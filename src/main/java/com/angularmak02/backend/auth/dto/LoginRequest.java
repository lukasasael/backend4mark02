package com.angularmak02.backend.auth.dto;

public record LoginRequest(
    String email,
    String password
) {
}