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
import java.util.UUID;

@Service
public class MonthlyBudgetService {
    private MonthlyBudgetRepository monthlyBudgetRepository;

    @Autowired
    public MonthlyBudgetService(MonthlyBudgetRepository monthlyBudgetRepository) {
        this.monthlyBudgetRepository = monthlyBudgetRepository;
    }

    @Transactional
    public MonthlyBudget updateOriginalAmount(UUID userId, BigDecimal originalAmount) {
        MonthlyBudget budget = monthlyBudgetRepository.findById(userId)
                .orElseThrow(() -> new MonthlyBudgetNotFoundException("Monthly budget not found with id: " + userId));

        budget.setOriginalAmount(originalAmount);
        return monthlyBudgetRepository.save(budget);
    }
}
