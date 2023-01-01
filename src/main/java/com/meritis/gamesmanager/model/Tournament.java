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
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournament_seq")
    @SequenceGenerator(name = "tournament_seq", sequenceName = "tournament_id_seq", allocationSize = 1)
    @Column
    private int id;

    @Column
    private String name;

    public Tournament(String name) {
        this.name = name;
    }
}
