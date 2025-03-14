package com.WinSaving.api.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "saving_goal")
public class SavingGoal {//lombok recomenda a notacao @getter e @setter, substitui os metodos?

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String purpose;

    private String depositPlace;

    private Double goalAmount;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDepositPlace() {
        return depositPlace;
    }

    public void setDepositPlace(String depositPlace) {
        this.depositPlace = depositPlace;
    }

    public Double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
