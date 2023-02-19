package com.meritis.gamesmanager.model.helpers;

public class TeamRequest {
    public String shortName;
    public String fullName;
    public int shape;

    public TeamRequest(String shortName, String fullName, int shape) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.shape = shape;
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

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }
}
