package com.meritis.gamesmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
public class Team {
    @Id
    @Column
    private String shortName;
    @Column
    private String fullName;
    @Column
    private int fifaRank;
    @Column
    private int shape;
    @Column
    private String groupName;

    public Team(String shortName, String fullName, int fifaRank, int shape, String groupName) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.fifaRank = fifaRank;
        this.shape = shape;
        this.groupName = groupName;
    }
}
