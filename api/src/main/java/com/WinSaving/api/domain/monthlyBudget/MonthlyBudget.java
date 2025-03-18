package com.WinSaving.api.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "monthly_budget")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyBudget {

    @Id
    @GeneratedValue
    private UUID id;

    private Double amount;

    @OneToMany(mappedBy = "monthlyBudget", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();


}

