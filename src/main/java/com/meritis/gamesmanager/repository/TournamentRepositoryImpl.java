package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Tournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TournamentRepositoryImpl implements TournamentRepository {
    private final JdbcTemplate jdbcTemplate;

    public TournamentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Tournament tournament) {
        jdbcTemplate.update("INSERT INTO tournaments(name) VALUES(?)", tournament.getName());
    }
}