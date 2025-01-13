package com.web.backend.domain.repository.objective;

import com.web.backend.domain.model.objective.Objective;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
}
