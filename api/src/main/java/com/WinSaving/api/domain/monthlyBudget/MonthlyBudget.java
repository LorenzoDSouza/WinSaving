package com.WinSaving.api.domain.monthlyBudget;

import com.WinSaving.api.domain.expense.Expense;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "monthly_budgets")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyBudget {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double usedAmount = 0.00;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double originalAmount;

    private int payDay;

    private Date lastReset;

    @OneToMany(mappedBy = "monthlyBudget", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();
}


