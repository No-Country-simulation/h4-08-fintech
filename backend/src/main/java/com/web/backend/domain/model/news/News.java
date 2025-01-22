package com.web.backend.domain.model.news;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SQLDelete(sql = "UPDATE news SET deleted = true where id = ?")
@SQLRestriction("deleted=false")
public class News {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String author;
    @Column(name = "publish_date")
    private LocalDateTime publishDate;
    @ManyToOne
    @JoinColumn(name = "news_source_id")
    private NewsSource source;
    @ManyToOne
    @JoinColumn(name = "news_category_id")
    private NewsCategory category;
    private boolean deleted = false;
}
