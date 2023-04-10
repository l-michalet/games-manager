package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.repository.TeamRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @GetMapping("/tournament/{tournamentId}/teams/{teamInfoId}")
    @ApiOperation(value = "Find a team in a tournament", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the team"),
            @ApiResponse(code = 404, message = "Team not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Team> findTeam(@PathVariable("tournamentId") Integer tournamentId,
                                         @PathVariable("teamInfoId") Integer teamInfoId) {
        System.out.format("TeamApi | findTeam tournamentId=%s teamInfoId=%d", tournamentId, teamInfoId);
        Team team = teamRepository.findByTournamentIdAndTeamInfoId(tournamentId, teamInfoId)
                .orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping("/tournament/{tournamentId}/teams")
    @ApiOperation(value = "List all teams of a tournament", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all the teams"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<Team>> listTeams(@PathVariable("tournamentId") Integer tournamentId) {
        System.out.format("TeamApi | listTeams");
        return new ResponseEntity<>(teamRepository.findAllByTournamentId(tournamentId), HttpStatus.OK);
    }
}