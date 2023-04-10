package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public List<Team> saveAll(List<Team> teams) {
        teamRepository.saveAll(teams);
        return teams;
    }

    public Map<String, List<Integer>> allTeamIdsPerGroup(Integer tournamentId) {
        return teamRepository.findAllByTournamentId(tournamentId).stream()
                .filter(t -> t.getGroupName() != null)
                .collect(Collectors.groupingBy(Team::getGroupName, Collectors.mapping(Team::getTeamInfoId, Collectors.toList())));
    }
}