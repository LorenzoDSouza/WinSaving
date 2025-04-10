package com.WinSaving.api.service;

import com.WinSaving.api.domain.expense.ExpenseRequestDTO;
import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import com.WinSaving.api.exceptions.DateComparisonException;
import com.WinSaving.api.exceptions.ExpenseNotFoundException;
import com.WinSaving.api.exceptions.MonthlyBudgetNotFoundException;
import com.WinSaving.api.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.WinSaving.api.domain.expense.Expense;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final MonthlyBudgetService monthlyBudgetService;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, MonthlyBudgetService monthlyBudgetService) {
        this.expenseRepository = expenseRepository;
        this.monthlyBudgetService = monthlyBudgetService;
    }

    @Transactional
    public Expense createExpense(ExpenseRequestDTO dto, MonthlyBudget monthlyBudget) {
        if (dto.value().compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new IllegalArgumentException("Value of the expense must be greater than zero");
        }
        Expense expense = new Expense();

        expense.setValue(dto.value());
        expense.setExpenseType(dto.type());
        expense.setDescription(dto.description());
        expense.setDate(dto.date());
        expense.setMonthlyBudget(monthlyBudget);

        return expenseRepository.save(expense);
    }

    @Transactional
    public Expense updateValue (UUID expenseId, BigDecimal newValue) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + expenseId));
        MonthlyBudget monthlyBudget = expense.getMonthlyBudget();

        if (newValue.compareTo(expense.getValue()) <= 0) {
            throw new IllegalArgumentException("New value of the expense must be greater than zero!");
        }

        if (!monthlyBudgetService.expenseWasAfterLastReset(monthlyBudget, expense)) {
            throw new DateComparisonException
                    ("The expense value was not updated because the expense was made before the last reset!");
        }

        if (newValue.compareTo(expense.getValue()) == 0) {
            throw new IllegalArgumentException("New value of the expense is the same as the old value!");
        }

        expense.setValue(newValue);

        return expenseRepository.save(expense);
    }



}
