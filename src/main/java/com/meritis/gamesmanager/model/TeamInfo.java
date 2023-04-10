package com.meritis.gamesmanager.model;

public class TeamInfo {
    private Integer id;
    private String shortName;
    private String fullName;
    private Integer fifaRank;
    private Integer shape;

    public TeamInfo() {

    }

    public TeamInfo(String shortName, String fullName, Integer shape) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.shape = shape;
    }

    public TeamInfo(Integer id, String shortName, String fullName, Integer fifaRank, Integer shape) {
        this.id = id;
        this.shortName = shortName;
        this.fullName = fullName;
        this.fifaRank = fifaRank;
        this.shape = shape;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getFifaRank() {
        return fifaRank;
    }

    public void setFifaRank(Integer fifaRank) {
        this.fifaRank = fifaRank;
    }

    public Integer getShape() {
        return shape;
    }

    public void setShape(Integer shape) {
        this.shape = shape;
    }
}