package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.mapper.TournamentMapper;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.request.TournamentRequest;
import com.meritis.gamesmanager.model.response.TournamentResponse;
import com.meritis.gamesmanager.service.TournamentService;;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/tournaments")
public class TournamentApi {
    private final TournamentService tournamentService;

    @GetMapping
    public ResponseEntity<List<TournamentResponse>> listTournaments() {
        log.info("[TournamentApi] listTournaments");
        List<TournamentResponse> tournaments = tournamentService.listTournaments()
                .stream()
                .map(TournamentMapper.INSTANCE::tournamentToTournamentResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tournaments);
    }

    @PostMapping
    public ResponseEntity<TournamentResponse> createTournament(@RequestBody TournamentRequest tournamentRequest) {
        log.info("TournamentApi | createTournament name={}", tournamentRequest.getName());
        Tournament tournament = tournamentService.createTournament(tournamentRequest);
        return ResponseEntity
                .created(URI.create("/tournaments/" + tournament.getId()))
                .body(TournamentMapper.INSTANCE.tournamentToTournamentResponse(tournament));
    }

    @GetMapping("/{tournamentId}")
    public ResponseEntity<TournamentResponse> getTournamentById(@PathVariable Long tournamentId) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        if (tournament == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(TournamentMapper.INSTANCE.tournamentToTournamentResponse(tournament));
    }

    @PutMapping("/{tournamentId}")
    public ResponseEntity<TournamentResponse> updateTournament(@PathVariable Long tournamentId,
                                                               @RequestBody TournamentRequest tournamentRequest) {
        Tournament tournament = tournamentService.updateTournament(tournamentId, tournamentRequest);
        return ResponseEntity.ok(TournamentMapper.INSTANCE.tournamentToTournamentResponse(tournament));
    }

    @DeleteMapping("/{tournamentId}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long tournamentId) {
        tournamentService.deleteTournament(tournamentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{tournamentId}/next")
    public ResponseEntity<Void> continueTournament(@PathVariable Long tournamentId) {
        tournamentService.continueTournament(tournamentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{tournamentId}/teams")
    public ResponseEntity<List<Team>> addTeamsToTournament(@PathVariable Long tournamentId, @RequestBody List<Long> teamIds) {
        return ResponseEntity.ok(tournamentService.addTeamsToTournament(tournamentId, teamIds));
    }

    @GetMapping("/{tournamentId}/teams")
    public ResponseEntity<List<Team>> getTeamsInTournament(@PathVariable Long tournamentId) {
        return ResponseEntity.ok(tournamentService.getTeamsInTournament(tournamentId));
    }

    @DeleteMapping("/{tournamentId}/teams/{teamId}")
    public ResponseEntity<TournamentResponse> removeTeamFromTournament(@PathVariable Long tournamentId, @PathVariable Long teamId) {
        tournamentService.removeTeamFromTournament(tournamentId, teamId);
        return ResponseEntity.noContent().build();
    }

}
