package com.WinSaving.api.domain.savingGoal;

import com.WinSaving.api.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "saving_goal")
public class SavingGoal {//lombok recomenda a notacao @getter e @setter, substitui os metodos?

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String purpose;

    private String depositPlace;

    private Date dueDate;

    private Double goalAmount;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
