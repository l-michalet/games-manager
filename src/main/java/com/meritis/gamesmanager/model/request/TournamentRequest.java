package com.meritis.gamesmanager.model.request;


import java.time.LocalDate;
import java.util.List;

public class TournamentRequest {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int nbOfGroups;
    private boolean isDirectElimination;
    private List<Long> teamIds;

    public TournamentRequest() {
    }

    public TournamentRequest(String name, LocalDate startDate, LocalDate endDate, int nbOfGroups, boolean isDirectElimination, List<Long> teamIds) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nbOfGroups = nbOfGroups;
        this.isDirectElimination = isDirectElimination;
        this.teamIds = teamIds;
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

    public int getNbOfGroups() {
        return nbOfGroups;
    }

    public void setNbOfGroups(int nbOfGroups) {
        this.nbOfGroups = nbOfGroups;
    }

    public boolean isDirectElimination() {
        return isDirectElimination;
    }

    public void setDirectElimination(boolean directElimination) {
        isDirectElimination = directElimination;
    }

    public List<Long> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Long> teamIds) {
        this.teamIds = teamIds;
    }
}
