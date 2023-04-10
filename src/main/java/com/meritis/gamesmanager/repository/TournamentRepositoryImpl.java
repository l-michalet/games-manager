package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Tournament;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TournamentRepositoryImpl implements TournamentRepository {
    private final JdbcTemplate jdbcTemplate;

    public TournamentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tournament save(Tournament tournament) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tournaments(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tournament.getName());
            return ps;
        }, keyHolder);
        tournament.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return tournament;
    }


    @Override
    public Optional<Tournament> findById(Integer id) {
        List<Tournament> tournaments = jdbcTemplate.query("SELECT * FROM tournaments WHERE id=?", new BeanPropertyRowMapper<>(Tournament.class), id);
        return tournaments.isEmpty() ? Optional.empty() : Optional.of(tournaments.get(0));
    }

    @Override
    public List<Tournament> findAll() {
        return jdbcTemplate.query("SELECT * FROM tournaments", new BeanPropertyRowMapper<>(Tournament.class));
    }
}