package com.meritis.gamesmanager.model;

public class Game {
    private Integer id;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private String groupName;
    private Integer groupDay;
    private Integer homeGoals = 0;
    private Integer awayGoals = 0;

    public Game() {

    }

    public Game(Integer homeTeamId, Integer awayTeamId, String groupName, Integer groupDay) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.groupName = groupName;
        this.groupDay = groupDay;
    }

    public Game(Integer homeTeamId, Integer awayTeamId) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupDay() {
        return groupDay;
    }

    public void setGroupDay(Integer groupDay) {
        this.groupDay = groupDay;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }
}