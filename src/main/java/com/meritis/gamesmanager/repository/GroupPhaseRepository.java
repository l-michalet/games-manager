package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.GroupPhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPhaseRepository extends JpaRepository<GroupPhase, Long> {
}
