package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "nb_of_groups")
    private int nbOfGroups;

    @Column(name = "is_direct_elimination")
    private boolean isDirectElimination;

    @ManyToMany(mappedBy = "tournaments", cascade = CascadeType.ALL)
    private List<Team> teams = new ArrayList<>();

    public Tournament(String name, LocalDate startDate, LocalDate endDate, int nbOfGroups, boolean isDirectElimination, List<Team> teams) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nbOfGroups = nbOfGroups;
        this.isDirectElimination = isDirectElimination;
        this.teams = teams;
    }
}