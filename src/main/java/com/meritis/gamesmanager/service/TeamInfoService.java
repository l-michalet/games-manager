package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.TeamInfo;
import com.meritis.gamesmanager.repository.TeamInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamInfoService {
    private final TeamInfoRepository teamInfoRepository;

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

    public void saveAll(List<TeamInfo> teamInfos) {
        teamInfoRepository.saveAll(teamInfos);
    }
}
