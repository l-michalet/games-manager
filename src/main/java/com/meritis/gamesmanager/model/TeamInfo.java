package com.meritis.gamesmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
public class TeamInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "team_info_seq", sequenceName = "team_info_id_seq", allocationSize = 1)
    @Column
    private int id;

    @Column
    private String shortName;
    @Column
    private String fullName;
    @Column
    private int fifaRank;
    @Column
    private int shape;

    public TeamInfo(String shortName, String fullName, int shape) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.shape = shape;
    }
}
