package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Score {
    private int homeGoals;
    private int awayGoals;
}
