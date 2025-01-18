package com.web.backend.domain.model.news;

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
@SQLDelete(sql = "UPDATE news_category SET deleted = true where id = ?")
@SQLRestriction("deleted=false")
public class NewsCategory {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean deleted = false;
}
