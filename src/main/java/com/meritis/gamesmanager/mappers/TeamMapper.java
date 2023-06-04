package com.meritis.gamesmanager.mappers;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.response.TeamResponse;
import com.meritis.gamesmanager.model.response.TeamsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    public static TeamResponse teamToTeamResponse(Team team) {
        return new TeamResponse(team.getShortName(), team.getFullName());
    }

    public static TeamsResponse teamsToTeamsResponse(List<Team> teams) {
        List<TeamResponse> teamResponses = teams.stream().map(TeamMapper::teamToTeamResponse).collect(Collectors.toList());
        return new TeamsResponse(teamResponses);
    }
}
