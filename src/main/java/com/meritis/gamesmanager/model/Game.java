package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int homeTeamId;
    @Column
    private int awayTeamId;
    @Column
    private String groupName;
    @Column
    private int groupDay;
    @Column
    private int homeGoals = 0;
    @Column
    private int awayGoals = 0;

    public Game(int homeTeamId, int awayTeamId, String groupName, int groupDay) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.groupName = groupName;
        this.groupDay = groupDay;
    }

    public Game(int homeTeamId, int awayTeamId) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }
}
