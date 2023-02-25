package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.TeamInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public class TeamInfoRepositoryImpl implements TeamInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TeamInfoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(TeamInfo teamInfo) {
        int affectedRows;
        if (teamInfo.getId() == 0) { // new team
            affectedRows = jdbcTemplate.update("INSERT INTO team_infos (short_name, full_name, fifa_rank, shape) VALUES (?, ?, ?, ?)",
                    teamInfo.getShortName(),
                    teamInfo.getFullName(),
                    teamInfo.getFifaRank(),
                    teamInfo.getShape(),
                    teamInfo.getId(),
                    new GeneratedKeyHolder());
        } else { // existing team, update
            affectedRows = jdbcTemplate.update("UPDATE team_infos SET short_name = ?, full_name = ?, fifa_rank = ?, shape = ? WHERE id = ?",
                teamInfo.getShortName(),
                teamInfo.getFullName(),
                teamInfo.getFifaRank(),
                teamInfo.getShape(),
                teamInfo.getId());
        }
        if (affectedRows == 0) {
            throw new RuntimeException("Updating team failed, no rows affected.");
        }
    }

    @Override
    public void saveAll(List<TeamInfo> teamInfos) {
        jdbcTemplate.batchUpdate("INSERT INTO team_infos (short_name, full_name, fifa_rank, shape) VALUES (?, ?, ?, ?)",
            teamInfos, teamInfos.size(),
            (ps, teamInfo) -> {
                ps.setString(1, teamInfo.getShortName());
                ps.setString(2, teamInfo.getFullName());
                ps.setInt(3, teamInfo.getFifaRank());
                ps.setInt(4, teamInfo.getShape());
            });
    }

    @Override
    public Optional<TeamInfo> findById(Integer id) {
        List<TeamInfo> teamInfos = jdbcTemplate.query("SELECT * FROM team_infos WHERE id = ?", new BeanPropertyRowMapper<>(TeamInfo.class), id);
        return teamInfos.isEmpty() ? Optional.empty() : Optional.of(teamInfos.get(0));
    }

    @Override
    public List<TeamInfo> findAll() {
        return jdbcTemplate.query("SELECT * FROM team_infos", new BeanPropertyRowMapper<>(TeamInfo.class));
    }

    @Override
    public Set<String> findAllShortNames() {
        return new HashSet<>(jdbcTemplate.queryForList("SELECT short_name FROM team_infos", String.class));
    }
}
