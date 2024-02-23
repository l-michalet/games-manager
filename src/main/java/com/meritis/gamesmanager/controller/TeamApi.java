package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.mapper.TeamMapper;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.request.TeamRequest;
import com.meritis.gamesmanager.model.response.TeamResponse;
import com.meritis.gamesmanager.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/teams")
public class TeamApi {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamResponse>> listTeams() {
        log.info("[TeamApi] listTeams");
        List<Team> teams = teamService.listTeams();
        return new ResponseEntity<>(TeamMapper.INSTANCE.teamsToTeamsResponse(teams), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getTeamById(@PathVariable Long id) {
        log.info("[TeamApi] getTeamById id={}", id);
        Team team = teamService.getTeamById(id);
        if (team == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(TeamMapper.INSTANCE.teamToTeamResponse(team));
    }

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamRequest teamRequest) {
        log.info("[TeamApi] createTeam fullName={}", teamRequest.getFullName());
        Team team = teamService.createTeam(teamRequest);
        return ResponseEntity
                .created(URI.create("/teams/" + team.getId()))
                .body(TeamMapper.INSTANCE.teamToTeamResponse(team));
    }
}
