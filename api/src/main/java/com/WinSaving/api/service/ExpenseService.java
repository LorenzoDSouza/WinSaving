package com.WinSaving.api.service;

import com.WinSaving.api.domain.expense.ExpenseRequestDTO;
import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
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

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Transactional
    public Expense createExpense(ExpenseRequestDTO dto, MonthlyBudget monthlyBudget) {
        //passar o id do monthly budget na request atraves do monthly budget controller
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




}
