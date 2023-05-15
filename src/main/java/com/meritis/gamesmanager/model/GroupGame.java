package com.meritis.gamesmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "group_game")
public class GroupGame extends Game {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    private int day;

    public GroupGame() {
    }

    public GroupGame(Team homeTeam, Team awayTeam, int day) {
        super(homeTeam, awayTeam);
        this.day = day;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
