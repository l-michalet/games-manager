package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    Optional<Tournament> findTournamentById(Integer tournamentId);
    Tournament save(Tournament tournament);
}
