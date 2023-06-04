package com.meritis.gamesmanager.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentResponse {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isDirectElimination;
    private List<TeamResponse> teams;
}

