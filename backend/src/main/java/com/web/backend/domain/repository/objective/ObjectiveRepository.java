package com.web.backend.domain.repository.objective;

import com.web.backend.domain.model.objective.Objective;
import com.web.backend.domain.model.objective.ObjectiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjectiveRepository extends JpaRepository<Objective, Long> {

    List<Objective> findByObjectiveStatus(ObjectiveStatus objectiveStatus);
}
