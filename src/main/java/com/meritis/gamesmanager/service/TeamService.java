package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public void saveAll(List<Team> teams) {
        teamRepository.saveAll(teams);
    }

    public Map<String, List<String>> allTeamIdsPerGroup() {
        return teamRepository.findAll().stream()
                .filter(t -> t.getGroupName() != null)
                .collect(Collectors.groupingBy(Team::getGroupName, Collectors.mapping(Team::getShortName, Collectors.toList())));
    }
}
