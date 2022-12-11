package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Team {
    private String shortName;
    private String fullName;
    private int fifaRank;
    private int shape;

    public Team(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }
}
