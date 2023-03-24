package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.configuration.DatabaseManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}