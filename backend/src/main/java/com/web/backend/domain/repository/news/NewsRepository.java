package com.web.backend.domain.repository.news;


import com.web.backend.domain.model.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Query(value = "SELECT * FROM news i WHERE i.deleted = :deleted", nativeQuery = true)
    List<News> findAllByIsDeleted(@Param("deleted") boolean deleted);
}
