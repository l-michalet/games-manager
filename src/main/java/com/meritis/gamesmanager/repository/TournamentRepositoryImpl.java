package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.TeamInfo;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.configuration.DatabaseManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TournamentRepositoryImpl implements TournamentRepository {
    private final DatabaseManager databaseManager;

    public TournamentRepositoryImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void save(Tournament tournament) {
        try (Connection connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tournaments(name) VALUES(?)")) {
            preparedStatement.setString(1, tournament.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving tournament", e);
        }
    }

    @Override
    public Optional<Tournament> findById(Integer id) {
        try (Connection connection = databaseManager.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tournaments WHERE id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            Tournament tournament = null;
            if (result.next()) {
                String name = result.getString("name");
                tournament = new Tournament(id, name);
            }
            return Optional.ofNullable(tournament);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding tournament", e);
        }
    }

    @Override
    public List<Tournament> findAll() {
        List<Tournament> tournaments = new ArrayList<>();
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM tournaments")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                tournaments.add(new Tournament(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all tournaments", e);
        }
        return tournaments;
    }
}