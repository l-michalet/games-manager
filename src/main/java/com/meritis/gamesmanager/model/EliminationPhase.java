package com.meritis.gamesmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "elimination_phase")
public class EliminationPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Column(name= "type")
    private String type;

    @OneToMany(mappedBy = "eliminationPhase", cascade = CascadeType.ALL)
    private List<EliminationGame> games = new ArrayList<>();

    public EliminationPhase(Tournament tournament, String type, List<EliminationGame> games) {
        this.tournament = tournament;
        this.type = type;
        this.games = games;
    }
}