package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Game;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public List<Game> findAllByGroupNameAndGroupDay(String groupName, Integer groupDay) {
        return jdbcTemplate.query("SELECT * FROM games WHERE group_name=? AND group_day=?", new BeanPropertyRowMapper<>(Game.class), groupName, groupDay);
    }

    @Override
    public List<Game> findAllByGroupName(String groupName) {
        return jdbcTemplate.query("SELECT * FROM games WHERE group_name=?", new BeanPropertyRowMapper<>(Game.class), groupName);
    }

    @Override
    public List<Game> findAllByGroupDay(Integer groupDay) {
        return jdbcTemplate.query("SELECT * FROM games WHERE group_day=?", new BeanPropertyRowMapper<>(Game.class), groupDay);
    }

    @Override
    public List<Game> findAll() {
        return jdbcTemplate.query("SELECT * FROM games", new BeanPropertyRowMapper<>(Game.class));
    }

    @Override
    public Optional<Game> findById(Integer id) {
        List<Game> games = jdbcTemplate.query("SELECT * FROM games WHERE id=?", new BeanPropertyRowMapper<>(Game.class), id);
        return games.isEmpty() ? Optional.empty() : Optional.of(games.get(0));
    }
}

