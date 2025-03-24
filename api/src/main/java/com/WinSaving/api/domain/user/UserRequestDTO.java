package com.WinSaving.api.domain.user;

public record UserRequestDTO(
        String name,
        String email,
        String password,
        String phoneNumber
) {
}
