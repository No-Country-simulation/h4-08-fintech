package com.web.backend.domain.repository.objective;

import com.web.backend.domain.model.objective.ObjectiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjectiveStatusRepository extends JpaRepository<ObjectiveStatus, Long> {

    @Query(value = "SELECT * FROM objective_status i WHERE i.deleted = :deleted", nativeQuery = true)
    List<ObjectiveStatus> findAllByIsDeleted(@Param("deleted") boolean deleted);
}
