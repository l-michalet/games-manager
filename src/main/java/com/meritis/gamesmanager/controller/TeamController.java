package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.TeamRequest;
import com.meritis.gamesmanager.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class TeamController {
    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/team")
    public ResponseEntity<Team> findTeam(@RequestParam("shortName") String shortName) {
        Optional<Team> optionalTeam = teamRepository.findById(shortName);
        if (optionalTeam.isEmpty()) {
            throw new RuntimeException();
        }
        return new ResponseEntity<>(optionalTeam.get(), HttpStatus.OK);
    }

    @PostMapping("/team")
    public ResponseEntity saveTeam(@RequestBody TeamRequest teamRequest) {
        Set<String> takenShortNames = teamRepository.findAllShortNames();
        if (takenShortNames.contains(teamRequest.getShortName())) {
            throw new RuntimeException();
        }
        if (teamRequest.getShape() < 0 || teamRequest.getShape() > 100) {
            throw new RuntimeException();
        }
        teamRepository.save(new Team(teamRequest.getShortName(), teamRequest.getFullName(), teamRequest.getShape()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> listTeams(@RequestParam("groupName") Optional<String> groupName) {
        return groupName.map(s -> new ResponseEntity<>(teamRepository.findAllByGroupName(s), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(teamRepository.findAll(), HttpStatus.OK));
    }

    @PostMapping("/team")
    public ResponseEntity saveTeams(@RequestBody List<TeamRequest> teamRequests) {
        // TODO: Bulk request
//        Set<String> takenShortNames = teamRepository.findAllShortNames();
//
//
//        if (takenShortNames.contains(teamRequest.getShortName())) {
//            throw new RuntimeException();
//        }
//        if (teamRequest.getShape() < 0 || teamRequest.getShape() > 100) {
//            throw new RuntimeException();
//        }
//        teamRepository.save(new Team(teamRequest.getShortName(), teamRequest.getFullName(), teamRequest.getShape()));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
