package com.meritis.gamesmanager.mappers;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.response.TeamResponse;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public static TeamResponse toResponse(Team team) {
        return new TeamResponse(team.getShortName(), team.getFullName());
    }

}
