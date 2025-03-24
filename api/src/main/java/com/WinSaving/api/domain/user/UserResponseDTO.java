package com.WinSaving.api.domain.user;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {}