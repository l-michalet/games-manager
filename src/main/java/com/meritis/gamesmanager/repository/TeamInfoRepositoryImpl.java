package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.TeamInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ConfigurationProperties(prefix = "spring.datasource")
public class TeamInfoRepositoryImpl implements TeamInfoRepository {

    private String url;
    private String username;
    private String password;

    @Override
    public void save(TeamInfo teamInfo) {

    }

    @Override
    public void saveAll(List<TeamInfo> teamInfo) {

    }

    @Override
    public Optional<TeamInfo> findById(Integer teamInfoId) {

        String query = "";

        try(Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<TeamInfo> findAll() {
        return null;
    }

    @Override
    public Set<String> findAllShortNames() {
        return null;
    }
}
