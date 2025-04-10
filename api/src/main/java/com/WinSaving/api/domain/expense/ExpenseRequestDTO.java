package com.WinSaving.api.domain.expense;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public record ExpenseRequestDTO(
        BigDecimal value,
        ExpenseType type,
        String description,
        Date date,
        UUID monthlyBudgetId
) {
}
