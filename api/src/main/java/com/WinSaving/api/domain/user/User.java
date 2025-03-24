package com.WinSaving.api.domain.user;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import com.WinSaving.api.domain.savingGoal.SavingGoal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Table(name = "users")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monthly_budget_id", referencedColumnName = "id")
    private MonthlyBudget monthlyBudget;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavingGoal> goals;

    public void setPassword(String rawPassword) {
        this.password = new BCryptPasswordEncoder().encode(rawPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", monthlyBudget id=" + monthlyBudget.getId() +
                '}';
    }
}
