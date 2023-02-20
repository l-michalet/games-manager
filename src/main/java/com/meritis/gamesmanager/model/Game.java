package com.meritis.gamesmanager.model;


public class Game {
    private int id;
    private int homeTeamId;
    private int awayTeamId;
    private String groupName;
    private int groupDay;
    private int homeGoals = 0;
    private int awayGoals = 0;

    public Game(int homeTeamId, int awayTeamId, String groupName, int groupDay) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.groupName = groupName;
        this.groupDay = groupDay;
    }

    public Game(int homeTeamId, int awayTeamId) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    public Game(int id, int homeTeamId, int awayTeamId, String groupName, int groupDay, int homeGoals, int awayGoals) {
        this.id = id;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.groupName = groupName;
        this.groupDay = groupDay;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupDay() {
        return groupDay;
    }

    public void setGroupDay(int groupDay) {
        this.groupDay = groupDay;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }
}
