package com.meritis.gamesmanager.model;

public class Results implements Comparable<Results> {
    private int teamId;
    private int gamesPlayed = 0;
    private int wins = 0;
    private int draws = 0;
    private int losses = 0;
    private int points = 0;
    private int goalsFor = 0;
    private int goalsAgainst = 0;
    private int goalsDiff = 0;

    public Results(int teamId) {
        this.teamId = teamId;
    }

    public void addGoalsFor(int newGoalsFor) {
        this.goalsFor += newGoalsFor;
        this.goalsDiff += newGoalsFor;
    }

    public void addGoalsAgainst(int newGoalsAgainst) {
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

    public int getTeamId() {
        return teamId;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalsDiff() {
        return goalsDiff;
    }

    public void setGoalsDiff(int goalsDiff) {
        this.goalsDiff = goalsDiff;
    }
}
