package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.mappers.TournamentMapper;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.request.TournamentRequest;
import com.meritis.gamesmanager.model.response.TournamentResponse;
import com.meritis.gamesmanager.service.TournamentService;;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/tournaments")
public class TournamentApi {
    private final TournamentService tournamentService;

    public TournamentApi(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("")
    public ResponseEntity<List<TournamentResponse>> listTournaments() {
        log.info("[TournamentApi] listTournaments");
        List<TournamentResponse> tournaments = tournamentService.listTournaments()
                .stream()
                .map(TournamentMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tournaments);
    }

    // Endpoint pour récupérer un tournament par id
    @GetMapping("/{id}")
    public ResponseEntity<TournamentResponse> getTournamentById(@PathVariable Long id) {
        Tournament tournament = tournamentService.getTournamentById(id);
        if (tournament == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(TournamentMapper.toResponse(tournament));
    }

    @PostMapping
    public ResponseEntity<TournamentResponse> createTournament(@RequestBody TournamentRequest tournamentRequest) {
        System.out.format("TournamentApi | createTournament name=%s", tournamentRequest.getName());
        Tournament tournament = tournamentService.createTournament(tournamentRequest);
        return ResponseEntity
                .created(URI.create("/tournaments/" + tournament.getId()))
                .body(TournamentMapper.toResponse(tournament));
    }

    @PostMapping("/{tournamentId}/start")
    public ResponseEntity<Void> startTournament(@PathVariable Long tournamentId) {
        tournamentService.startTournament(tournamentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{tournamentId}")
    public ResponseEntity<TournamentResponse> updateTournament(@PathVariable Long tournamentId,
                                                               @RequestBody TournamentRequest tournamentRequest) {
        Tournament tournament = tournamentService.updateTournament(tournamentId, tournamentRequest);
        return ResponseEntity.ok(TournamentMapper.toResponse(tournament));
    }

    @DeleteMapping("/{tournamentId}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long tournamentId) {
        tournamentService.deleteTournament(tournamentId);
        return ResponseEntity.noContent().build();
    }

}
