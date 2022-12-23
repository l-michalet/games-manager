package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @Column
    private String id;
    @Column
    private String homeTeam;
    @Column
    private String awayTeam;
    @Column
    private String groupName;
    @Column
    private int groupDay;
    @Column
    private int homeGoals = 0;
    @Column
    private int awayGoals = 0;

    public Game(String id, String homeTeam, String awayTeam, String groupName, int groupDay) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.groupName = groupName;
        this.groupDay = groupDay;
    }
}
