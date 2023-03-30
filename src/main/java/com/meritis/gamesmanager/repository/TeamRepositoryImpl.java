package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Team;
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
public class TeamRepositoryImpl implements TeamRepository {

    private final DatabaseManager databaseManager;

    public TeamRepositoryImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void save(Team team) {
        try (Connection connection = databaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO teams (team_info_id, tournament_id, group_name) VALUES (?, ?, ?)")) {
            statement.setInt(1, team.getTeamInfoId());
            statement.setInt(2, team.getTournamentId());
            statement.setString(3, team.getGroupName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving team ", e);
        }
    }

    @Override
    public void saveAll(List<Team> teams) {
        try (Connection connection = databaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO teams (team_info_id, tournament_id, group_name) VALUES (?, ?, ?)")) {
            for (Team team : teams) {
                statement.setInt(1, team.getTeamInfoId());
                statement.setInt(2, team.getTournamentId());
                statement.setString(3, team.getGroupName());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving all teams ", e);
        }
    }

    @Override
    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>();

        try (Connection connection = databaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int teamInfoId = resultSet.getInt("team_info_id");
                int tournamentId = resultSet.getInt("tournament_id");
                String groupName = resultSet.getString("group_name");
                teams.add(new Team(id, teamInfoId, tournamentId, groupName));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all teams ", e);
        }
        return teams;
    }

    @Override
    public List<Team> findAllByTournamentId(int tournamentId) {
        List<Team> teams = new ArrayList<>();

        try (Connection connection = databaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams WHERE tournament_id = ?")) {
            statement.setInt(1, tournamentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int teamInfoId = resultSet.getInt("team_info_id");
                String groupName = resultSet.getString("group_name");
                teams.add(new Team(id, teamInfoId, tournamentId, groupName));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all teams by tournamentId", e);
        }

        return teams;
    }

    @Override
    public Optional<Team> findByTournamentIdAndTeamInfoId(int tournamentId, int teamInfoId) {
        return Optional.empty();
    }
}
