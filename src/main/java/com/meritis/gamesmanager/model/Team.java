package com.meritis.gamesmanager.model;

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
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int teamInfoId;

    @Column
    private int tournamentId;

    @Column
    private String groupName;

    public Team(int teamInfoId, int tournamentId, String groupName) {
        this.teamInfoId = teamInfoId;
        this.tournamentId = tournamentId;
        this.groupName = groupName;
    }
}
