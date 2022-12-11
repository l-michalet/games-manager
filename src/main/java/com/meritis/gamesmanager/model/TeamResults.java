package com.meritis.gamesmanager.model;

import lombok.Data;

@Data
public class TeamResults implements Comparable<TeamResults> {
    private String shortName;
    private int gamesPlayed = 0;
    private int wins = 0;
    private int draws = 0;
    private int losses = 0;
    private int points = 0;
    private int goalsFor = 0;
    private int goalsAgainst = 0;
    private int goalsDiff = 0;

    public TeamResults(String shortName) {
        this.shortName = shortName;
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
        this.points +=3;
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
    public int compareTo(TeamResults teamResults) {
        int result = Integer.compare(teamResults.points, this.points);
        if (result != 0) { return result; }

        result = Integer.compare(teamResults.goalsDiff, this.goalsDiff);
        if (result != 0) { return result; }

        return Integer.compare(teamResults.goalsFor, this.goalsFor);
    }

    public String toString() {
        return shortName + " | " + gamesPlayed + " | " + wins + " | " + draws + " | " + losses
                + " | " + goalsFor + " | " + goalsAgainst + " | " + goalsDiff + " || " + points+ " |";
    }
}
