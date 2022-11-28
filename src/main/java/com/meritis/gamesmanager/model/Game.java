package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Game {
    private Team home;
    private Team away;
    private boolean played;
    private Team winner;
    private Score score;

    public Game(Team home, Team away) {
        this.home = home;
        this.away = away;
        played = false;
    }
}
