package com.meritis.gamesmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("group")
public class GroupGame extends Game {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "\"day\"")
    private int day;

    public GroupGame(Team homeTeam, Team awayTeam, int day) {
        super(homeTeam, awayTeam);
        this.day = day;
    }
}
