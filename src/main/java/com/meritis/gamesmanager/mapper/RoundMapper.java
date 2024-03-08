package com.meritis.gamesmanager.mapper;

import com.meritis.gamesmanager.model.Round;
import com.meritis.gamesmanager.model.response.RoundResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoundMapper {

    RoundMapper INSTANCE = Mappers.getMapper(RoundMapper.class);

    RoundResponse roundToRoundResponse(Round round);

}
