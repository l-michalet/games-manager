package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Game {
    private String id;
    private String home;
    private String away;
    private Score score;

    public Game(String id, String home, String away) {
        this.id = id;
        this.home = home;
        this.away = away;
    }
}
