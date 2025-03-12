package com.WinSaving.api.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue
    private UUID id;

    private String description;
    private Double value;

    @ManyToOne
    @JoinColumn(name = "monthly_budget_id")
    private MonthlyBudget monthlyBudget;

}

