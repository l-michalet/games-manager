package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    List<Team> findAllByGroupName(String groupName);

    @Query("SELECT t.shortName FROM Team t")
    Set<String> findAllShortNames();

}
