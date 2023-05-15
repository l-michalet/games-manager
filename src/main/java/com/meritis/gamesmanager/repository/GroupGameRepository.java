package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.GroupGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupGameRepository extends JpaRepository<GroupGame, Long> {
}
