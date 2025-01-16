package com.web.backend.domain.model.news;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class News {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String author;
    @Column(name = "publish_date")
    private LocalDate publishDate;
    @ManyToOne
    @JoinColumn(name = "news_source_id")
    private NewsSource source;
    @ManyToOne
    @JoinColumn(name = "news_category_id")
    private NewsCategory category;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
