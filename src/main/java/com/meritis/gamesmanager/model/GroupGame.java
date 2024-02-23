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

    @Column(name = "group_day")
    private int groupDay;

    public GroupGame(Team homeTeam, Team awayTeam, int groupDay) {
        super(homeTeam, awayTeam);
        this.groupDay = groupDay;
    }
}
