package com.WinSaving.api.service;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import com.WinSaving.api.domain.user.User;
import com.WinSaving.api.exceptions.MonthlyBudgetNotFoundException;
import com.WinSaving.api.repositories.MonthlyBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

@Service
public class MonthlyBudgetService {
    private MonthlyBudgetRepository monthlyBudgetRepository;

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
        MonthlyBudget budget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new MonthlyBudgetNotFoundException("Monthly budget not found with id: " + monthlyBudgetId));

        return budget;
    }


    @Transactional
    public MonthlyBudget renewOriginalAmount(UUID monthlyBudgetId) { //call this by a useEffect.
        MonthlyBudget budget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new MonthlyBudgetNotFoundException("Monthly budget not found with id: " + monthlyBudgetId));

        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        if(budget.getPayDay() == dayOfMonth) {
            budget.setUsedAmount(BigDecimal.ZERO);
        }

        return monthlyBudgetRepository.save(budget);
    }
}
