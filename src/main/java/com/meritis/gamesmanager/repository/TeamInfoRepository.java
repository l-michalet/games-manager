package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.TeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TeamInfoRepository extends JpaRepository<TeamInfo, Integer> {

    Optional<TeamInfo> findById(Integer teamInfoId);

    Optional<TeamInfo> findByShortName(String shortName);

    List<TeamInfo> findAll();

    @Query("SELECT t.shortName FROM TeamInfo t")
    Set<String> findAllShortNames();

}
