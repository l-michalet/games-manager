package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Tournament;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository {

    void save(Tournament tournament);
}
