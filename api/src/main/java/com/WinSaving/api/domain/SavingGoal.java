package com.WinSaving.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "saving_goal")
public class SavingGoal {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String purpose;

    private String depositPlace;

    private Double goalAmount;
    private Double totalAmount;
}
