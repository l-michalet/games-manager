package com.meritis.gamesmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("elimination")
public class EliminationGame extends Game {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elimination_phase_id")
    private EliminationPhase eliminationPhase;

    public EliminationGame(Team homeTeam, Team awayTeam, EliminationPhase eliminationPhase) {
        super(homeTeam, awayTeam);
        this.eliminationPhase = eliminationPhase;
    }
}
