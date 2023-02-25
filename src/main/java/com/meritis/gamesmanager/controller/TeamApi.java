package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TeamApi {

    private final TeamRepository teamRepository;

    public TeamApi(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/tournament/{tournamentId}/team/{teamInfoId}")
    public ResponseEntity<Team> findTeam(@PathVariable("tournamentId") Integer tournamentId,
                                         @PathVariable("teamInfoId") Integer teamInfoId) {
        System.out.format("TeamApi | findTeam tournamentId=%s teamInfoId=%d", tournamentId, teamInfoId);
        Team team = teamRepository.findByTournamentIdAndTeamInfoId(tournamentId, teamInfoId)
                .orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping("/tournament/{tournamentId}/team")
    public ResponseEntity<List<Team>> listTeams(@PathVariable("tournamentId") Integer tournamentId) {
        System.out.format("TeamApi | listTeams");
        return new ResponseEntity<>(teamRepository.findAllByTournamentId(tournamentId), HttpStatus.OK);
    }
}
