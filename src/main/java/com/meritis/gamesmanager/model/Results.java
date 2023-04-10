package com.meritis.gamesmanager.model;

public class Results implements Comparable<Results> {
    private Integer teamId;
    private Integer gamesPlayed = 0;
    private Integer wins = 0;
    private Integer draws = 0;
    private Integer losses = 0;
    private Integer points = 0;
    private Integer goalsFor = 0;
    private Integer goalsAgainst = 0;
    private Integer goalsDiff = 0;

    public Results() {

    }

    public Results(Integer teamId) {
        this.teamId = teamId;
    }

    public void addGoalsFor(Integer newGoalsFor) {
        this.goalsFor += newGoalsFor;
        this.goalsDiff += newGoalsFor;
    }

    public void addGoalsAgainst(Integer newGoalsAgainst) {
        this.goalsAgainst += newGoalsAgainst;
        this.goalsDiff -= newGoalsAgainst;
    }

    public void win() {
        this.wins++;
        this.points+=3;
        this.gamesPlayed++;
    }

    public void draw() {
        this.draws++;
        this.points++;
        this.gamesPlayed++;
    }

    public void loss() {
        this.losses++;
        this.gamesPlayed++;
    }

    @Override
    public int compareTo(Results results) {
        int result = Integer.compare(results.points, this.points);
        if (result != 0) { return result; }

        result = Integer.compare(results.goalsDiff, this.goalsDiff);
        if (result != 0) { return result; }

        return Integer.compare(results.goalsFor, this.goalsFor);
    }

    public String toString() {
        return teamId + " | " + gamesPlayed + " | " + wins + " | " + draws + " | " + losses
                + " | " + goalsFor + " | " + goalsAgainst + " | " + goalsDiff + " || " + points+ " |";
    }

    // Getters and Setters

    public Integer getTeamId() {
        return teamId;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Integer getGoalsDiff() {
        return goalsDiff;
    }

    public void setGoalsDiff(Integer goalsDiff) {
        this.goalsDiff = goalsDiff;
    }
}