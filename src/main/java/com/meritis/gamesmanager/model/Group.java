package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Group {
    private String name;
    private List<String> teamIds;
    private List<GroupDay> groupDays;
    private int currentDay;

    public Group(String name, List<String> teamIds) {
        this.name = name;
        this.teamIds = teamIds;
        this.currentDay = 0;
    }
}
