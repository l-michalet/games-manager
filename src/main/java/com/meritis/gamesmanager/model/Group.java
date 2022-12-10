package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Group {
    private String name;
    private List<Team> teams;
    private int currentRound;
    private List<GroupDay> groupDays;

    public Group(String name, List<Team> teams) {
        this.name = name;
        this.teams = teams;
        this.currentRound = 0;
    }
}
