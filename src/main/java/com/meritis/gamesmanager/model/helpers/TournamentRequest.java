package com.meritis.gamesmanager.model.helpers;


import java.util.List;

public class TournamentRequest {
    public String name;
    public List<Integer> teamInfoIds;
    public int nbOfGroups;

    public TournamentRequest(String name, List<Integer> teamInfoIds, int nbOfGroups) {
        this.name = name;
        this.teamInfoIds = teamInfoIds;
        this.nbOfGroups = nbOfGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getTeamInfoIds() {
        return teamInfoIds;
    }

    public void setTeamInfoIds(List<Integer> teamInfoIds) {
        this.teamInfoIds = teamInfoIds;
    }

    public int getNbOfGroups() {
        return nbOfGroups;
    }

    public void setNbOfGroups(int nbOfGroups) {
        this.nbOfGroups = nbOfGroups;
    }
}
