package com.WinSaving.api.service;

import com.WinSaving.api.domain.expense.ExpenseRequestDTO;
import com.WinSaving.api.domain.expense.ExpenseType;
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
import java.sql.Date;
import java.time.LocalDate;
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
    public Expense createExpense(ExpenseRequestDTO dto, UUID monthlyBudgetId) {
        MonthlyBudget monthlyBudget = monthlyBudgetService.getMonthlyBudget(monthlyBudgetId);

        if (dto.value().compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new IllegalArgumentException("Value of the expense must be greater than zero");
        }
        Expense expense = new Expense();

        expense.setValue(dto.value());
        expense.setExpenseType(dto.type());
        expense.setDescription(dto.description());
        if(dto.date()!=null){
            expense.setDate(dto.date());
        } else {
        expense.setDate(Date.valueOf(LocalDate.now()));
        }
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

        if (!monthlyBudgetService.expenseWasAfterLastReset(expense)) {
            throw new DateComparisonException
                    ("The expense value was not updated because the expense was made before the last reset!");
        }

        if (newValue.compareTo(expense.getValue()) == 0) {
            throw new IllegalArgumentException("New value of the expense is the same as the old value!");
        }

        expense.setValue(newValue);

        return expenseRepository.save(expense);
    }

    @Transactional
    public Expense updateExpenseType(UUID expenseId, ExpenseType newExpenseType) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + expenseId));

        if(expense.getExpenseType().equals(newExpenseType)){
            throw new IllegalArgumentException("New expense type cannot be the same as the old expense type!");
        }

        expense.setExpenseType(newExpenseType);
        return expenseRepository.save(expense);
    }

    @Transactional
    public Expense updateDescription (UUID expenseId, String newDescription) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + expenseId));

        if(expense.getDescription().equals(newDescription)){
            throw new IllegalArgumentException("New description cannot be the same as the old description!");
        }

        expense.setDescription(newDescription);
        return expenseRepository.save(expense);
    }

    @Transactional
    public Expense updateDate(UUID expenseId, Date newDate) {
        /*
        TODO: input logic will be in the frontend
                expense was before lastReset?
                    yes -> ask if the value will be added to the usedAmount
                    no -> do nothing
         */
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + expenseId));

        if(expense.getDate().equals(newDate)){
            throw new IllegalArgumentException("New date cannot be the same as the old date!");
        }

        expense.setDate(newDate);
        return expenseRepository.save(expense);
    }

    @Transactional
    public void deleteExpense(UUID expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + expenseId));
        MonthlyBudget monthlyBudget = expense.getMonthlyBudget();

        if(monthlyBudgetService.expenseWasAfterLastReset(expense)){
            monthlyBudgetService.subtractValueToUsedAmountByExpense(expense);
            System.out.println("The value of the used amount in the monthly budget was changed, because the expense was after the last reset");
        } else {
            System.out.println("The value of the used amount in the monthly budget wasn't changed, because the expense was before the last reset");
        }

        expenseRepository.delete(expense);
    }

}
