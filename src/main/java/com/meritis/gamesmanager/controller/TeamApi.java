package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TeamApi {

    private final TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> listTeams() {
        log.info("[TeamApi] listTeams");
        return new ResponseEntity<>(teamService.listTeams(), HttpStatus.OK);
    }

}
