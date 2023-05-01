package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public List<Team> saveAll(List<Team> teams) {
        return teamRepository.saveAll(teams);
    }

    public Map<String, List<Integer>> allTeamIdsPerGroup(int tournamentId) {
        return teamRepository.findAllByTournamentId(tournamentId).stream()
                .filter(t -> t.getGroupName() != null)
                .collect(Collectors.groupingBy(Team::getGroupName, Collectors.mapping(Team::getTeamInfoId, Collectors.toList())));
    }
}
