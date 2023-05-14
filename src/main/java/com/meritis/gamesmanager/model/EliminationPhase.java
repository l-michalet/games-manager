package com.meritis.gamesmanager.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "elimination_phase")
public class EliminationPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    private String type;

    @OneToMany(mappedBy = "eliminationPhase", cascade = CascadeType.ALL)
    private List<EliminationGame> games;


}