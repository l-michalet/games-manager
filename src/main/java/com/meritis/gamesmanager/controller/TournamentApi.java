package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.request.TournamentRequest;
import com.meritis.gamesmanager.model.response.TournamentResponse;
import com.meritis.gamesmanager.service.TeamService;
import com.meritis.gamesmanager.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/tournaments")
public class TournamentApi {
    private final TournamentService tournamentService;
    private final TeamService teamService;

    public TournamentApi(TournamentService tournamentService, TeamService teamService) {
        this.tournamentService = tournamentService;
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TournamentResponse> createTournament(@RequestBody TournamentRequest tournamentRequest) {
        System.out.format("TournamentApi | createTournament name=%s", tournamentRequest.getName());
        TournamentResponse tournamentResponse = tournamentService.createTournament(tournamentRequest);
        return ResponseEntity
                .created(URI.create("/tournaments/" + tournamentResponse.getId()))
                .body(tournamentResponse);
    }

}
