package com.meritis.gamesmanager.model;

import nonapi.io.github.classgraph.json.Id;

public class TeamInfo {
    @Id
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

    public TeamInfo() {}

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
