package com.WinSaving.api.domain.expense;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "expenses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseType expenseType;

    private String description;

    @ManyToOne
    @JoinColumn(name = "monthly_budget_id", nullable = false)
    private MonthlyBudget monthlyBudget;

    @Column(name = "date", nullable = false)
    private Date date;

}