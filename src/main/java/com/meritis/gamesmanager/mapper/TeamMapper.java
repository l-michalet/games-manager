package com.meritis.gamesmanager.mapper;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.response.TeamResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamResponse teamToTeamResponse(Team team);

    List<TeamResponse> teamsToTeamsResponse(List<Team> team);

}
