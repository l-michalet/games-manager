package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Tournament;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository {

    Tournament save(Tournament tournament);

    Optional<Tournament> findById(Integer tournamentId);

    List<Tournament> findAll();

}