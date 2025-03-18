package com.WinSaving.api.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Table(name = "user")
@Entity
@Setter
@Getter
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavingGoal> goals;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", monthlyBudget id=" + monthlyBudget.getId() +
                '}';
    }
}
