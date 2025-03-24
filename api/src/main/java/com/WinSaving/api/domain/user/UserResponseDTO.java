package com.WinSaving.api.domain.user;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudgetDTO;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String phoneNumber,
        MonthlyBudgetDTO monthlyBudget
) {}