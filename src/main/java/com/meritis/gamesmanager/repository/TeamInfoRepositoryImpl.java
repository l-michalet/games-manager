package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.TeamInfo;
import com.meritis.gamesmanager.configuration.DatabaseManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public class TeamInfoRepositoryImpl implements TeamInfoRepository {

    private final DatabaseManager databaseManager;

    public TeamInfoRepositoryImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void save(TeamInfo teamInfo) {
        try (Connection connection = databaseManager.getConnection()) {
            if (teamInfo.getId() == 0) { // new team
                String sql = "INSERT INTO team_infos (short_name, full_name, fifa_rank, shape) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, teamInfo.getShortName());
                statement.setString(2, teamInfo.getFullName());
                statement.setInt(3, teamInfo.getFifaRank());
                statement.setInt(4, teamInfo.getShape());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Inserting team failed, no rows affected.");
                }
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (!generatedKeys.next()) {
                    throw new SQLException("Inserting team failed, no ID obtained.");
                }
                teamInfo.setId(generatedKeys.getInt(1));
            } else { // existing team, update
                String sql = "UPDATE team_infos SET short_name = ?, full_name = ?, fifa_rank = ?, shape = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, teamInfo.getShortName());
                statement.setString(2, teamInfo.getFullName());
                statement.setInt(3, teamInfo.getFifaRank());
                statement.setInt(4, teamInfo.getShape());
                statement.setInt(5, teamInfo.getId());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Updating team failed, no rows affected.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving teamInfo", e);
        }
    }

    @Override
    public void saveAll(List<TeamInfo> teamInfos) {
        for (TeamInfo teamInfo : teamInfos) {
            this.save(teamInfo);
        }
    }

    @Override
    public Optional<TeamInfo> findById(Integer id) {
        try (Connection connection = databaseManager.getConnection()){
            String sql = "SELECT * FROM team_infos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            TeamInfo teamInfo = null;
            if (result.next()) {
                String shortName = result.getString("short_name");
                String fullName = result.getString("full_name");
                int fifaRank = result.getInt("fifa_rank");
                int shape = result.getInt("shape");
                teamInfo = new TeamInfo(id, shortName, fullName, fifaRank, shape);
            }
            return Optional.ofNullable(teamInfo);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding teamInfo", e);
        }
    }

    @Override
    public List<TeamInfo> findAll() {
        List<TeamInfo> teamInfos = new ArrayList<>();
        try (Connection connection = databaseManager.getConnection()) {
            String sql = "SELECT * FROM team_infos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String shortName = result.getString("short_name");
                String fullName = result.getString("full_name");
                int fifaRank = result.getInt("fifa_rank");
                int shape = result.getInt("shape");
                teamInfos.add(new TeamInfo(id, shortName, fullName, fifaRank, shape));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding teamInfos", e);
        }
        return teamInfos;
    }

    @Override
    public Set<String> findAllShortNames() {
        Set<String> shortNames = new HashSet<>();
        try (Connection connection = databaseManager.getConnection()){
            String sql = "SELECT short_name FROM team_infos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String shortName = result.getString("short_name");
                shortNames.add(shortName);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding short names", e);
        }
        return shortNames;
    }
}
