package com.WinSaving.api.domain.savingGoal;

import java.math.BigDecimal;
import java.sql.Date;

public record SavingGoalRequestDTO(
        String name,
        String purpose,
        String depositPlace,
        Date dueDate,
        BigDecimal goalAmount
) {
}
