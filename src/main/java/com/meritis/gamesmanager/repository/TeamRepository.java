package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository {

    void save(Team team);
    void saveAll(List<Team> teams);
    List<Team> findAll();
    List<Team> findAllByTournamentId(Integer tournamentId);

    Optional<Team> findByTournamentIdAndTeamInfoId(Integer tournamentId, Integer teamInfoId);
}