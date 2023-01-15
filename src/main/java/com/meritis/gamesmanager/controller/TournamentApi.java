package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.helpers.TournamentRequest;
import com.meritis.gamesmanager.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TournamentApi {
    private static int nbOfTournaments = 0;

    private final TournamentService tournamentService;

    public TournamentApi(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping("/tournament")
    public ResponseEntity createTournament(@RequestBody TournamentRequest tournamentRequest) {
        System.out.println("TOURNAMENT number " + (++nbOfTournaments));
        System.out.println("TournamentApi | createTournament name=" +  tournamentRequest.getName());
        tournamentService.createTournament(tournamentRequest.getName(), tournamentRequest.getTeamInfoIds(), tournamentRequest.getNbOfGroups());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
