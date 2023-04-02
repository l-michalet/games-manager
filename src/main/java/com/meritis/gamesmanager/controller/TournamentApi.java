package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.helpers.TournamentRequest;
import com.meritis.gamesmanager.repository.TournamentRepository;
import com.meritis.gamesmanager.service.TournamentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TournamentApi {
    private final TournamentService tournamentService;
    private final TournamentRepository tournamentRepository;

    public TournamentApi(TournamentService tournamentService, TournamentRepository tournamentRepository) {
        this.tournamentService = tournamentService;
        this.tournamentRepository = tournamentRepository;
    }

    @PostMapping("/tournament")
    @ApiOperation(value = "Create a new tournament", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the tournament"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Void> createTournament(@RequestBody TournamentRequest tournamentRequest) {
        System.out.format("TournamentApi | createTournament name=%s",tournamentRequest.getName());
        tournamentService.createTournament(tournamentRequest.getName(), tournamentRequest.getTeamInfoIds(), tournamentRequest.getNbOfGroups());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tournament/{tournamentId}")
    @ApiOperation(value = "Find a tournament", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the tournament"),
            @ApiResponse(code = 404, message = "Tournament not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Tournament> findTournament(@PathVariable("tournamentId") int tournamentId) {
        System.out.format("TournamentApi | findTournament tournamentId=%s", tournamentId);
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(tournament, HttpStatus.OK);
    }

    @GetMapping("/tournament")
    @ApiOperation(value = "List all tournaments", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the tournament"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<Tournament>> listTournaments() {
        System.out.format("TournamentApi | listTournaments");
        return new ResponseEntity<>(tournamentRepository.findAll(), HttpStatus.OK);
    }
}
