package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.TeamInfo;
import com.meritis.gamesmanager.repository.TeamInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamInfoService {
    private final TeamInfoRepository teamInfoRepository;

    public List<TeamInfo> getAllTeamInfos() {
        return teamInfoRepository.findAll();
    }

    public void saveAll(List<TeamInfo> teamInfos) {
        teamInfoRepository.saveAll(teamInfos);
    }
}
