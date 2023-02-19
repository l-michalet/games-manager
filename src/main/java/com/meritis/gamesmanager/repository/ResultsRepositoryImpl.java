package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Results;

import java.util.List;
import java.util.Optional;

public class ResultsRepositoryImpl implements ResultsRepository {

    @Override
    public void save(Results results) {

    }

    @Override
    public Optional<Results> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Results> findAllByIds(List<Integer> ids) {
        return null;
    }
}
