package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.TeamInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TeamInfoRepository {

    void save(TeamInfo teamInfo);
    void saveAll(List<TeamInfo> teamInfo);

    Optional<TeamInfo> findById(Integer teamInfoId);

    Optional<TeamInfo> findByShortName(String shortName);

    List<TeamInfo> findAll();

    Set<String> findAllShortNames();

}