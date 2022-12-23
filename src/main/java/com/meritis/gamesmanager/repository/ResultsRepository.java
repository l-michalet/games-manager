package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsRepository extends JpaRepository<Results, String> {

}
