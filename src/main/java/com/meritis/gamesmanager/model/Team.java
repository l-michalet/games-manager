package com.meritis.gamesmanager.model;

public class Team {
    private int id;
    private int teamInfoId;
    private int tournamentId;
    private String groupName;

    public Team(int teamInfoId, int tournamentId, String groupName) {
        this.teamInfoId = teamInfoId;
        this.tournamentId = tournamentId;
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamInfoId() {
        return teamInfoId;
    }

    public void setTeamInfoId(int teamInfoId) {
        this.teamInfoId = teamInfoId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
