package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + teamId));
    }

    public List<Team> listTeams() {
        return teamRepository.findAll();
    }

    public List<Team> saveAll(List<Team> teams) {
        return teamRepository.saveAll(teams);
    }


}
