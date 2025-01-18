package com.web.backend.domain.repository.news;

import com.web.backend.domain.model.news.NewsSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {
    @Query(value = "SELECT * FROM news_source i WHERE i.deleted = :deleted", nativeQuery = true)
    List<NewsSource> findAllByIsDeleted(@Param("deleted") boolean deleted);
}
