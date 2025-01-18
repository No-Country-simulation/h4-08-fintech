package com.web.backend.domain.model.objective;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SQLDelete(sql = "UPDATE objective_status SET deleted = true where id = ?")
@SQLRestriction("deleted=false")
public class ObjectiveStatus {
    @Id
    @GeneratedValue
    public Long id;
    public String name;
    @Column(name = "deleted")
    private boolean deleted = false;
}
