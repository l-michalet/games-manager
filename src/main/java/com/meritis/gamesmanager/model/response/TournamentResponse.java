package com.meritis.gamesmanager.model.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TournamentResponse {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TeamResponse> teams;
    private RoundResponse currentRound;
    private TeamResponse winner;

}

