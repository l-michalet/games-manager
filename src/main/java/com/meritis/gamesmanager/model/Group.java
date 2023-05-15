package com.meritis.gamesmanager.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "group_table")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String groupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_phase_id")
    private GroupPhase groupPhase;

    @ManyToMany
    @JoinTable(name = "group_team",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupGame> groupGames;

    // Constructors

    public Group() {
    }

    public Group(String groupName, List<Team> teams, List<GroupGame> groupGames) {
        this.groupName = groupName;
        this.teams = teams;
        this.groupGames = groupGames;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupPhase getGroupPhase() {
        return groupPhase;
    }

    public void setGroupPhase(GroupPhase groupPhase) {
        this.groupPhase = groupPhase;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<GroupGame> getGroupGames() {
        return groupGames;
    }

    public void setGroupGames(List<GroupGame> groupGames) {
        this.groupGames = groupGames;
    }
}