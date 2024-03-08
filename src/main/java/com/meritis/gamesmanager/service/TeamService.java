package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.request.TeamRequest;
import com.meritis.gamesmanager.repository.TeamRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public List<Team> getAllTeamsById(List<Long> teamIds) {
        return teamRepository.findAllById(teamIds);
    }

    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + teamId));
    }

    public Team saveTeam(TeamRequest teamRequest) {
        Team team = new Team(teamRequest.getShortName(),teamRequest.getFullName());
        return teamRepository.save(team);
    }

    public List<Team> saveAll(List<Team> teams) {
        return teamRepository.saveAll(teams);
    }

    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }

}
