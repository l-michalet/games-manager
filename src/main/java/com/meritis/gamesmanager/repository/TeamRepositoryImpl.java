package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    private final JdbcTemplate jdbcTemplate;

    public TeamRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Team team) {
        int affectedRows = jdbcTemplate.update("INSERT INTO teams (team_info_id, tournament_id, group_name) VALUES (?, ?, ?)",
                team.getTeamInfoId(),
                team.getTournamentId(),
                team.getGroupName());
        if (affectedRows != 1) {
            throw new RuntimeException("Error saving team");
        }
    }

    @Override
    public void saveAll(List<Team> teams) {
        jdbcTemplate.batchUpdate("INSERT INTO teams (team_info_id, tournament_id, group_name) VALUES (?, ?, ?)",
            teams, teams.size(),
            (ps, team) -> {
                ps.setInt(1, team.getTeamInfoId());
                ps.setInt(2, team.getTournamentId());
                ps.setString(3, team.getGroupName());
            });
    }

    @Override
    public List<Team> findAll() {
        return jdbcTemplate.query("SELECT * FROM teams", new TeamRowMapper());
    }

    @Override
    public List<Team> findAllByTournamentId(Integer tournamentId) {
        return jdbcTemplate.query("SELECT * FROM teams WHERE tournament_id = ?", new TeamRowMapper(), tournamentId);
    }

    @Override
    public Optional<Team> findByTournamentIdAndTeamInfoId(Integer tournamentId, Integer teamInfoId) {
        List<Team> teams = jdbcTemplate.query("SELECT * FROM teams WHERE tournament_id = ? AND team_info_id=?", new TeamRowMapper(), tournamentId, teamInfoId);
        return teams.isEmpty() ? Optional.empty() : Optional.of(teams.get(0));
    }

    private static class TeamRowMapper implements RowMapper<Team> {

        @Override
        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            int teamInfoId = rs.getInt("team_info_id");
            int tournamentId = rs.getInt("tournament_id");
            String groupName = rs.getString("group_name");
            return new Team(id, teamInfoId, tournamentId, groupName);
        }
    }
}
