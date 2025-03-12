package com.WinSaving.api.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monthly_budget_id", referencedColumnName = "id")
    private MonthlyBudget monthlyBudget;


}
