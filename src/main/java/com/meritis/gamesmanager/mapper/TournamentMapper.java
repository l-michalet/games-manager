package com.meritis.gamesmanager.mapper;

import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.response.TournamentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TournamentMapper {

    TournamentMapper INSTANCE = Mappers.getMapper(TournamentMapper.class);

    TournamentResponse tournamentToTournamentResponse(Tournament tournament);

}

