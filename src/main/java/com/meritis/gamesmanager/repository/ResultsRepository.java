package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Results;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultsRepository {

    void save(Results results);

    Optional<Results> findById(int id);

    Optional<Results> findByTeamId(String teamId);

    List<Results> findAllByIds(List<Integer> ids);

    List<Results> findAll();
}
