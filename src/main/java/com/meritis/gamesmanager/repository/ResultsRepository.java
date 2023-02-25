package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultsRepository extends JpaRepository<Results, Integer> {

    Optional<Results> findByTeamId(String teamId);
}
