package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.configuration.DatabaseManager;
import com.meritis.gamesmanager.model.TeamInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepositoryImpl implements GameRepository {

    private final JdbcTemplate jdbcTemplate;

    public GameRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Game game) {
        int affectedRows = jdbcTemplate.update("INSERT INTO games (home_team_id, away_team_id, group_name, group_day) VALUES (?, ?, ?, ?)",
            game.getHomeTeamId(),
            game.getAwayTeamId(),
            game.getGroupName(),
            game.getGroupDay());
        if (affectedRows != 1) {
            throw new RuntimeException("Error saving game");
        }
    }

    @Override
    public List<Game> findAll() {
        return jdbcTemplate.query("SELECT * FROM games", new BeanPropertyRowMapper<>(Game.class));
    }
}

