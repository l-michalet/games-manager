package com.meritis.gamesmanager.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private List<Team> teams;

    public Tournament() {
    }

    public Tournament(String name, LocalDate startDate, LocalDate endDate, int nbOfGroups, boolean isDirectElimination, List<Team> teams) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nbOfGroups = nbOfGroups;
        this.isDirectElimination = isDirectElimination;
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNbOfGroups() {
        return nbOfGroups;
    }

    public void setNbOfGroups(int nbOfGroups) {
        this.nbOfGroups = nbOfGroups;
    }

    public boolean isDirectElimination() {
        return isDirectElimination;
    }

    public void setDirectElimination(boolean directElimination) {
        isDirectElimination = directElimination;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}