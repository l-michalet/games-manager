package com.meritis.gamesmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "matches")
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @Column(name = "home_score")
    private int homeScore;

    @Column(name = "away_score")
    private int awayScore;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "ended")
    private boolean ended;

    @ManyToOne
    private Round round;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = LocalDate.now();
    }

    public Team getWinner() {
        if (!ended) {
            return null;
        }
        if (homeScore > awayScore) {
            return homeTeam;
        } else if (homeScore < awayScore) {
            return awayTeam;
        } else {
            return Math.random() > 0.5 ? homeTeam : awayTeam;
        }
    }
}
