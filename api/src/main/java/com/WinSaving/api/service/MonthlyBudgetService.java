package com.WinSaving.api.service;

import com.WinSaving.api.domain.expense.Expense;
import com.WinSaving.api.domain.expense.ExpenseRequestDTO;
import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import com.WinSaving.api.domain.user.User;
import com.WinSaving.api.exceptions.DateComparisonException;
import com.WinSaving.api.exceptions.MonthlyBudgetNotFoundException;
import com.WinSaving.api.repositories.MonthlyBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class MonthlyBudgetService {
    private final MonthlyBudgetRepository monthlyBudgetRepository;

    @Autowired
    public MonthlyBudgetService(MonthlyBudgetRepository monthlyBudgetRepository) {
        this.monthlyBudgetRepository = monthlyBudgetRepository;
    }

    @Transactional
    public MonthlyBudget updateOriginalAmount(UUID monthlyBudgetId, BigDecimal originalAmount) {
        MonthlyBudget budget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new MonthlyBudgetNotFoundException("Monthly budget not found with id: " + monthlyBudgetId));

        budget.setOriginalAmount(originalAmount);
        return monthlyBudgetRepository.save(budget);
    }

    @Transactional
    public MonthlyBudget updatePayDay(UUID monthlyBudgetId, int payDay) {
        MonthlyBudget budget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new MonthlyBudgetNotFoundException("Monthly budget not found with id: " + monthlyBudgetId));

        budget.setPayDay(payDay);
        return monthlyBudgetRepository.save(budget);
    }

    @Transactional
    public MonthlyBudget getMonthlyBudget(UUID monthlyBudgetId) {
        return monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new MonthlyBudgetNotFoundException("Monthly budget not found with id: " + monthlyBudgetId));
    }


    @Transactional
    public MonthlyBudget resetUsedAmount(UUID monthlyBudgetId) {
        MonthlyBudget budget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new MonthlyBudgetNotFoundException("Monthly budget not found with id: " + monthlyBudgetId));

        BigDecimal remainderMoney = budget.getOriginalAmount().subtract(budget.getUsedAmount());

        budget.setUsedAmount(remainderMoney.negate());

        budget.setLastReset(new Date(System.currentTimeMillis()));

        System.out.println("Used amount reset! (Payday!!!). New used amount: " + budget.getUsedAmount());

        return monthlyBudgetRepository.save(budget);
    }

    @Transactional
    public boolean checkDateToResetUsedAmount(UUID monthlyBudgetId) {
        MonthlyBudget budget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new MonthlyBudgetNotFoundException("Monthly budget not found with id: " + monthlyBudgetId));

        int todayDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        if (Objects.equals(budget.getPayDay(), todayDayOfMonth)) {
            resetUsedAmount(monthlyBudgetId);
            return true;
        }

        return false;
    }

    @Transactional
    public MonthlyBudget updateUsedAmount(UUID monthlyBudgetId, BigDecimal usedAmount) {
        MonthlyBudget budget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new MonthlyBudgetNotFoundException("Monthly budget not found with id: " + monthlyBudgetId));

        budget.setUsedAmount(usedAmount);
        return monthlyBudgetRepository.save(budget);
    }

    @Transactional
    public Date getLastResetDate(UUID monthlyBudgetId) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new MonthlyBudgetNotFoundException("Monthly budget not found with id: " + monthlyBudgetId));

        return monthlyBudget.getLastReset();
    }

    @Transactional
    public boolean expenseWasAfterLastReset(Expense expense) {
        MonthlyBudget monthlyBudget = expense.getMonthlyBudget();
        int status = monthlyBudget.getLastReset().compareTo(expense.getDate());
        return status < 0;
    }

    @Transactional
    public MonthlyBudget addValueToUsedAmountByValue(MonthlyBudget monthlyBudget, BigDecimal value){
        BigDecimal newUsedAmount = monthlyBudget.getUsedAmount().add(value);

        monthlyBudget.setUsedAmount(newUsedAmount);
        return monthlyBudgetRepository.save(monthlyBudget);
    }

    @Transactional
    public MonthlyBudget subtractValueToUsedAmountByValue(MonthlyBudget monthlyBudget, BigDecimal value){
        BigDecimal newUsedAmount = monthlyBudget.getUsedAmount().subtract(value);

        monthlyBudget.setUsedAmount(newUsedAmount);
        return monthlyBudgetRepository.save(monthlyBudget);
    }

    @Transactional
    public MonthlyBudget subtractValueToUsedAmountByExpense(Expense expense) {
        MonthlyBudget monthlyBudget = expense.getMonthlyBudget();

        BigDecimal newUsedAmount = monthlyBudget.getUsedAmount().subtract(expense.getValue());

        monthlyBudget.setUsedAmount(newUsedAmount);
        return monthlyBudgetRepository.save(monthlyBudget);
    }

}
