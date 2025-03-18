package com.WinSaving.api.domain;

import com.WinSaving.api.domain.enums.ExpenseType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "expense")
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
    @JoinColumn(name = "monthly_budget_id")
    private MonthlyBudget monthlyBudget;

}

