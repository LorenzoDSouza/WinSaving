package com.WinSaving.api.domain.user;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudgetDTO;

public record UserRequestDTO(
        String name,
        String email,
        String password,
        String phoneNumber,
        MonthlyBudgetDTO monthlyBudget
) {
}
