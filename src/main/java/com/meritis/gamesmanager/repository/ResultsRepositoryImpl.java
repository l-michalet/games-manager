package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Results;
import com.meritis.gamesmanager.configuration.DatabaseManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ResultsRepositoryImpl implements ResultsRepository {

    private final DatabaseManager dataManager;

    public ResultsRepositoryImpl(DatabaseManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void save(Results results) {
        try (Connection connection = dataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO results (team_id, games_played, wins, draws, losses, points, goals_for, goals_against, goals_diff) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, results.getTeamId());
            statement.setInt(2, results.getGamesPlayed());
            statement.setInt(3, results.getWins());
            statement.setInt(4, results.getDraws());
            statement.setInt(5, results.getLosses());
            statement.setInt(6, results.getPoints());
            statement.setInt(7, results.getGoalsFor());
            statement.setInt(8, results.getGoalsAgainst());
            statement.setInt(9, results.getGoalsDiff());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save results", e);
        }
    }

    @Override
    public Optional<Results> findById(int id) {
        try (Connection connection = dataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM results WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResults(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find results by id", e);
        }
    }

    @Override
    public List<Results> findAllByIds(List<Integer> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        try (Connection connection = dataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM results WHERE id IN (" + String.join(",", Collections.nCopies(ids.size(), "?")) + ")")) {
            for (int i = 0; i < ids.size(); i++) {
                statement.setInt(i + 1, ids.get(i));
            }
            ResultSet rs = statement.executeQuery();
            List<Results> results = new ArrayList<>();
            while (rs.next()) {
                results.add(mapResults(rs));
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find results by ids", e);
        }
    }

    private Results mapResults(ResultSet rs) throws SQLException {
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




