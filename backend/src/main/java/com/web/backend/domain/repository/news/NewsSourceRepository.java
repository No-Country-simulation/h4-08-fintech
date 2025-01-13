package com.web.backend.domain.repository.news;

import com.web.backend.domain.model.news.NewsSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {
}
