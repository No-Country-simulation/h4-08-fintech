package com.web.backend.domain.repository.objective;

import com.web.backend.domain.model.objective.ObjectiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectiveStatusRepository extends JpaRepository<ObjectiveStatus, Long> {
}
