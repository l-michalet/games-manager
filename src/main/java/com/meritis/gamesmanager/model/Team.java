package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Team {
    private String name;
    private int fifaRank;
    private int shape;

    public Team(String name) {
        this.name = name;
    }
}
