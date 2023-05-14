package com.meritis.gamesmanager.model.request;


import java.time.LocalDate;
import java.util.List;

public class TournamentRequest {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isDirectElimination;
    private List<Long> teamIds;

    public TournamentRequest() {
    }

    public TournamentRequest(String name, LocalDate startDate, LocalDate endDate, boolean isDirectElimination) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDirectElimination = isDirectElimination;
    }

    // getters et setters

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

    public void setDirectElimination(boolean isDirectElimination) {
        this.isDirectElimination = isDirectElimination;
    }

    public List<Long> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Long> teamIds) {
        this.teamIds = teamIds;
    }
}
