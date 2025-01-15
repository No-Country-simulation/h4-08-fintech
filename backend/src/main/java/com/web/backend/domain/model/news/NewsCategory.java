package com.web.backend.domain.model.news;

import jakarta.persistence.Column;
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
public class NewsCategory {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
