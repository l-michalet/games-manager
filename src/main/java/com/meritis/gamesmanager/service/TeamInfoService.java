package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.TeamInfo;
import com.meritis.gamesmanager.repository.TeamInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamInfoService {
    private final TeamInfoRepository teamInfoRepository;

    public TeamInfoService(TeamInfoRepository teamInfoRepository) {
        this.teamInfoRepository = teamInfoRepository;
    }

    public List<TeamInfo> getAllTeamInfos() {
        return teamInfoRepository.findAll();
    }

    public List<TeamInfo> getTeamInfos(List<Integer> teamInfoIds) {
        List<TeamInfo> teamInfos = new ArrayList<>();
        for (Integer teamInfoId : teamInfoIds) {
            Optional<TeamInfo> optionalTeamInfo = teamInfoRepository.findById(teamInfoId);
            if (optionalTeamInfo.isEmpty()) {
                throw new RuntimeException(); // TODO: change error
            }
            teamInfos.add(optionalTeamInfo.get());
        }
        return teamInfos;
    }

    @Transactional
    public void saveAll(List<TeamInfo> teamInfos) {
        teamInfoRepository.saveAll(teamInfos);
    }
}
