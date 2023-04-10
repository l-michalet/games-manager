package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.TeamInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO team_infos (short_name, full_name, fifa_rank, shape) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, teamInfo.getShortName());
            ps.setString(2, teamInfo.getFullName());
            if (teamInfo.getFifaRank() == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, teamInfo.getFifaRank());
            }
            if (teamInfo.getShape() == null) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setInt(4, teamInfo.getShape());
            }
            return ps;
        }, keyHolder);
        teamInfo.setId(keyHolder.getKey().intValue());
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
    public Optional<TeamInfo> findByShortName(String shortName) {
        List<TeamInfo> teamInfos = jdbcTemplate.query("SELECT * FROM team_infos WHERE short_name = ?", new BeanPropertyRowMapper<>(TeamInfo.class), shortName);
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
