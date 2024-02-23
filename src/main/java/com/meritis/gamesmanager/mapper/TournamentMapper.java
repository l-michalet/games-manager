package com.meritis.gamesmanager.mapper;

import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.response.TeamResponse;
import com.meritis.gamesmanager.model.response.TournamentResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TournamentMapper {

    public static TournamentResponse toResponse(Tournament tournament) {
        List<TeamResponse> teamsResponse = tournament.getTeams().stream()
                .map(TeamMapper.INSTANCE::teamToTeamResponse)
                .collect(Collectors.toList());
        return new TournamentResponse(tournament.getName(), tournament.getStartDate(), tournament.getEndDate(), tournament.isDirectElimination(), teamsResponse);
    }
}

