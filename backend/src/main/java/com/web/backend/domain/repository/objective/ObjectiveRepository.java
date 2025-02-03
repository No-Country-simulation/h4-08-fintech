package com.web.backend.domain.repository.objective;

import com.web.backend.domain.model.objective.Objective;
import com.web.backend.domain.model.objective.ObjectiveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjectiveRepository extends JpaRepository<Objective, Long> {

    List<Objective> findByObjectiveStatus(ObjectiveStatus objectiveStatus);

    @Query(value = "SELECT * FROM objective i WHERE i.deleted = :deleted", nativeQuery = true)
    List<Objective> findAllByIsDeleted(@Param("deleted") boolean deleted);
    List<Objective> findByCustomerId(Long customerId);
    List<Objective> findByCustomerId(Long customerId, Sort sort);
    Page<Objective> findByCustomerId(Long customerId, Pageable pageable);

}
