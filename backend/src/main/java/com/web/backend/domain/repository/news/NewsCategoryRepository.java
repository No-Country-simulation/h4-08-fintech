package com.web.backend.domain.repository.news;

import com.web.backend.domain.model.news.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Long> {
    @Query(value = "SELECT * FROM news_category i WHERE i.deleted = :deleted", nativeQuery = true)
    List<NewsCategory> findAllByIsDeleted(@Param("deleted") boolean deleted);
}
