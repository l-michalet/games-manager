package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.configuration.DatabaseManager;
import com.meritis.gamesmanager.model.TeamInfo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepositoryImpl implements GameRepository {

    private final DatabaseManager databaseManager;

    public GameRepositoryImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void save(Game game) {
        try (Connection connection = databaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO games (home_team_id, away_team_id, group_name, group_day) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, game.getHomeTeamId());
            ps.setInt(2, game.getAwayTeamId());
            ps.setString(3, game.getGroupName());
            ps.setInt(4, game.getGroupDay());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                game.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Game> findAllByGroupNameAndGroupDay(String groupName, Integer groupDay) {
        try (Connection connection = databaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM games WHERE group_name=? AND group_day=?")) {
            ps.setString(1, groupName);
            ps.setInt(2, groupDay);
            ResultSet rs = ps.executeQuery();
            List<Game> games = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int homeTeamId = rs.getInt("home_team_id");
                int awayTeamId = rs.getInt("away_team_id");
                int homeGoals = rs.getInt("home_goals");
                int awayGoals = rs.getInt("away_goals");
                games.add(new Game(id, homeTeamId, awayTeamId, groupName, groupDay, homeGoals, awayGoals));
            }
            return games;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Game> findAllByGroupName(String groupName) {
        try (Connection connection = databaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM games WHERE group_name=?")) {
            ps.setString(1, groupName);
            ResultSet rs = ps.executeQuery();
            List<Game> games = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int homeTeamId = rs.getInt("home_team_id");
                int awayTeamId = rs.getInt("away_team_id");
                int groupDay = rs.getInt("group_day");
                int homeGoals = rs.getInt("home_goals");
                int awayGoals = rs.getInt("away_goals");
                games.add(new Game(id, homeTeamId, awayTeamId, groupName, groupDay, homeGoals, awayGoals));
            }
            return games;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Game> findAllByGroupDay(Integer groupDay) {
        try (Connection connection = databaseManager.getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT * FROM games WHERE group_day=?")) {
            ps.setInt(1, groupDay);
            ResultSet rs = ps.executeQuery();
            List<Game> games = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int homeTeamId = rs.getInt("home_team_id");
                int awayTeamId = rs.getInt("away_team_id");
                String groupName = rs.getString("group_name");
                int homeGoals = rs.getInt("home_goals");
                int awayGoals = rs.getInt("away_goals");
                games.add(new Game(id, homeTeamId, awayTeamId, groupName, groupDay, homeGoals, awayGoals));
            }
            return games;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Game> findAll() {
        try (Connection connection = databaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM games")) {
            ResultSet rs = ps.executeQuery();
            List<Game> games = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int homeTeamId = rs.getInt("home_team_id");
                int awayTeamId = rs.getInt("away_team_id");
                String groupName = rs.getString("group_name");
                int groupDay = rs.getInt("group_day");
                int homeGoals = rs.getInt("home_goals");
                int awayGoals = rs.getInt("away_goals");
                games.add(new Game(id, homeTeamId, awayTeamId, groupName, groupDay, homeGoals, awayGoals));
            }
            return games;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Game> findById(Integer id) {
        try (Connection connection = databaseManager.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM games WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Game game = null;
            if (rs.next()) {
                int homeTeamId = rs.getInt("home_team_id");
                int awayTeamId = rs.getInt("away_team_id");
                String groupName = rs.getString("group_name");
                int groupDay = rs.getInt("group_day");
                int homeGoals = rs.getInt("home_goals");
                int awayGoals = rs.getInt("away_goals");
                game = new Game(id, homeTeamId, awayTeamId, groupName, groupDay, homeGoals, awayGoals);
            }
            return Optional.ofNullable(game);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding game", e);
        }
    }
}

