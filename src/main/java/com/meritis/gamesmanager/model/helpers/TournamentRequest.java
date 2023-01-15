package com.meritis.gamesmanager.model.helpers;

import lombok.Data;

import java.util.List;

@Data
public class TournamentRequest {
    private String name;
    private List<Integer> teamInfoIds;
    private int nbOfGroups;
}
