package com.web.backend.domain.model.objective;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Objective {
    @Id
    @GeneratedValue
    private Long id;
//    private Customer customer;
    private String title;
    private String description;
    @Column(name = "target_amount")
    private Float targetAmount;
    @Column(name = "current_amount")
    private Float currentAmount;
    @Column(name = "due_date")
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "objective_status_id")
    private ObjectiveStatus objectiveStatus;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
