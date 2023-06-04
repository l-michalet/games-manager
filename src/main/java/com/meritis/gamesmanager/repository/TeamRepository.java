package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findTeamsByTournamentsIsContaining(Tournament tournament);
}
