package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    List<Team> findAllByTournamentId(int tournamentId);

    Optional<Team> findByTournamentIdAndTeamInfoId(int tournamentId, int teamInfoId);
}
