package com.meritis.gamesmanager.model.response;

import java.time.LocalDate;
import java.util.List;

public class TournamentResponse {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isDirectElimination;
    private List<TeamResponse> teams;

    public TournamentResponse() {}

    public TournamentResponse(Long id, String name, LocalDate startDate, LocalDate endDate,
                              boolean isDirectElimination, List<TeamResponse> teams) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDirectElimination = isDirectElimination;
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isDirectElimination() {
        return isDirectElimination;
    }

    public void setDirectElimination(boolean directElimination) {
        isDirectElimination = directElimination;
    }

    public List<TeamResponse> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamResponse> teams) {
        this.teams = teams;
    }
}

