package com.WinSaving.api.domain;

import com.WinSaving.api.domain.enums.ExpenseType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
    private String description;

    private Double value;

    @ManyToOne
    @JoinColumn(name = "monthly_budget_id")
    private MonthlyBudget monthlyBudget;


    //getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public MonthlyBudget getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(MonthlyBudget monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}

