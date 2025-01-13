package com.web.backend.domain.repository.news;


import com.web.backend.domain.model.news.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
