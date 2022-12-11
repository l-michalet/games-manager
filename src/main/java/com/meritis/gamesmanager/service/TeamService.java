package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<String> getAllTeamIds() {
        return teamRepository.findAll().stream()
            .map(Team::getShortName)
            .collect(Collectors.toList());
    }
}
