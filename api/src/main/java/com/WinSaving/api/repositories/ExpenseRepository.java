package com.WinSaving.api.repositories;

import com.WinSaving.api.domain.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
}
