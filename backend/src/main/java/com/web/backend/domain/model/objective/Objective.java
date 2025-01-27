package com.web.backend.domain.model.objective;

import com.web.backend.domain.model.Customer.Customer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SQLDelete(sql = "UPDATE objective SET deleted = true where id = ?")
@SQLRestriction("deleted=false")
public class Objective {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String title;
    private String description;
    @Column(name = "target_amount")
    private Float targetAmount;
    @Column(name = "current_amount")
    private Float currentAmount;
    private int progress;
    @Column(name = "due_date")
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "objective_status_id")
    private ObjectiveStatus objectiveStatus;
    private boolean deleted = false;
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;
}
