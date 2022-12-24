package com.meritis.gamesmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
public class Results implements Comparable<Results> {
    @Id
    @Column
    private String teamName;
    @Column
    private int gamesPlayed = 0;
    @Column
    private int wins = 0;
    @Column
    private int draws = 0;
    @Column
    private int losses = 0;
    @Column
    private int points = 0;
    @Column
    private int goalsFor = 0;
    @Column
    private int goalsAgainst = 0;
    @Column
    private int goalsDiff = 0;

    public Results(String teamName) {
        this.teamName = teamName;
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
        return teamName + " | " + gamesPlayed + " | " + wins + " | " + draws + " | " + losses
                + " | " + goalsFor + " | " + goalsAgainst + " | " + goalsDiff + " || " + points+ " |";
    }
}
