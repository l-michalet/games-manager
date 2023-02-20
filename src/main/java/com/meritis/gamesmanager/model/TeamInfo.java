package com.meritis.gamesmanager.model;

public class TeamInfo {
    private int id;
    private String shortName;
    private String fullName;
    private int fifaRank;
    private int shape;

    public TeamInfo(String shortName, String fullName, int shape) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.shape = shape;
    }

    public TeamInfo(int id, String shortName, String fullName, int fifaRank, int shape) {
        this.id = id;
        this.shortName = shortName;
        this.fullName = fullName;
        this.fifaRank = fifaRank;
        this.shape = shape;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getFifaRank() {
        return fifaRank;
    }

    public void setFifaRank(int fifaRank) {
        this.fifaRank = fifaRank;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }
}
