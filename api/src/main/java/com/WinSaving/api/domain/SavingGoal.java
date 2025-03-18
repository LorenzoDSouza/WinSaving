package com.WinSaving.api.domain;

import jakarta.persistence.*;

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
