package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Results;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ResultsRepositoryImpl implements ResultsRepository {

    private final JdbcTemplate jdbcTemplate;

    public ResultsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Results results) {
        int affectedRows = jdbcTemplate.update("INSERT INTO results (team_id, games_played, wins, draws, losses, points, goals_for, goals_against, goals_diff) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            results.getTeamId(),
            results.getGamesPlayed(),
            results.getWins(),
            results.getDraws(),
            results.getLosses(),
            results.getPoints(),
            results.getGoalsFor(),
            results.getGoalsAgainst(),
            results.getGoalsDiff());
        if (affectedRows != 1) {
            throw new RuntimeException("Error saving team");
        }
    }

    @Override
    public Optional<Results> findById(int id) {
        List<Results> results = jdbcTemplate.query("SELECT * FROM results WHERE id = ?", new BeanPropertyRowMapper<>(Results.class), id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public List<Results> findAllByIds(List<Integer> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        String sql = "SELECT * FROM results WHERE id IN (" + String.join(",", Collections.nCopies(ids.size(), "?")) + ")";
        return jdbcTemplate.query(sql, ids.toArray(), new ResultsMapper());
    }

    private static class ResultsMapper implements RowMapper<Results> {
        @Override
        public Results mapRow(ResultSet rs, int rowNum) throws SQLException {
            Results results = new Results(rs.getInt("team_id"));
            results.setGamesPlayed(rs.getInt("games_played"));
            results.setWins(rs.getInt("wins"));
            results.setDraws(rs.getInt("draws"));
            results.setLosses(rs.getInt("losses"));
            results.setPoints(rs.getInt("points"));
            results.setGoalsFor(rs.getInt("goals_for"));
            results.setGoalsAgainst(rs.getInt("goals_against"));
            results.setGoalsDiff(rs.getInt("goals_diff"));
            return results;
        }
    }
}




