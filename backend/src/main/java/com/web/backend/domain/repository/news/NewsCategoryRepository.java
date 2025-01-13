package com.web.backend.domain.repository.news;

import com.web.backend.domain.model.news.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Long> {
}
