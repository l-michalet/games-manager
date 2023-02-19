package com.meritis.gamesmanager.model.helpers;

import com.meritis.gamesmanager.model.Game;

import java.util.List;
public class GroupDayResponse {
    public int day;
    public List<Game> games;

    public GroupDayResponse(int day, List<Game> games) {
        this.day = day;
        this.games = games;
    }


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
