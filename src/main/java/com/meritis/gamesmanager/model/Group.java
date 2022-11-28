package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
public class Group {
    private List<Team> teams;
    private int currentRound;
    private Map<Game, Integer> schedule;

    public Group(List<Team> teams) {
        this.teams = teams;
        this.currentRound = 0;
    }
}
