package com.WinSaving.api.domain.expense;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "expenses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue
    private UUID id;
    private Double value;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
    private String description;

    @ManyToOne
    @JoinColumn(name = "monthly_budget_id", nullable = false)
    private MonthlyBudget monthlyBudget;

}