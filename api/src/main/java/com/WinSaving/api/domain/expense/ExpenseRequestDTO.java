package com.WinSaving.api.domain.expense;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public record ExpenseRequestDTO(
        BigDecimal value,
        ExpenseType type,
        String description,
        Date date
) {
}
