package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rounds")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "round_number")
    private int roundNumber;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private List<Match> matches;

    @Column(name = "completed")
    private boolean completed;

    @OneToOne
    private Tournament tournament;

    public Round(int roundNumber, List<Match> matches) {
        this.roundNumber = roundNumber;
        this.matches = matches;
        this.completed = false;
    }

}
