package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.mappers.TeamMapper;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.response.TeamResponse;
import com.meritis.gamesmanager.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + teamId));
    }

    public List<Team> getAllTeamsByTournamentId(Long tournamentId) {
        return teamRepository.findAllByTournamentId(tournamentId);
    }

    public List<Team> saveAll(List<Team> teams) {
        return teamRepository.saveAll(teams);
    }


}
