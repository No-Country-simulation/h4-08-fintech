package com.web.backend.domain.model.objective;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ObjectiveStatus {
    @Id
    @GeneratedValue
    public Long id;
    public String name;
}
