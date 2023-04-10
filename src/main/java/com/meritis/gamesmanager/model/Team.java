package com.meritis.gamesmanager.model;

public class Team {
    private Integer id;
    private Integer teamInfoId;
    private Integer tournamentId;
    private String groupName;

    public Team(Integer teamInfoId, Integer tournamentId, String groupName) {
        this.teamInfoId = teamInfoId;
        this.tournamentId = tournamentId;
        this.groupName = groupName;
    }

    public Team(Integer id, Integer teamInfoId, Integer tournamentId, String groupName) {
        this.id = id;
        this.teamInfoId = teamInfoId;
        this.tournamentId = tournamentId;
        this.groupName = groupName;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamInfoId() {
        return teamInfoId;
    }

    public void setTeamInfoId(Integer teamInfoId) {
        this.teamInfoId = teamInfoId;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}