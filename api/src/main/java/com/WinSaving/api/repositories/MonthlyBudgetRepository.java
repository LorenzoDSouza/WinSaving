package com.WinSaving.api.repositories;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, UUID> {
}
