package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.TeamInfo;
import com.meritis.gamesmanager.model.helpers.TeamRequest;
import com.meritis.gamesmanager.repository.TeamInfoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TeamInfoController {
    private final TeamInfoRepository teamInfoRepository;

    public TeamInfoController(TeamInfoRepository teamInfoRepository) {
        this.teamInfoRepository = teamInfoRepository;
    }

    @GetMapping("/team")
    public ResponseEntity<TeamInfo> findTeamInfo(@RequestParam("shortName") int teamInfoId) {
        System.out.format("[TeamInfoController] findTeam shortName=%s", teamInfoId);
        Optional<TeamInfo> optionalTeam = teamInfoRepository.findById(teamInfoId);
        if (optionalTeam.isEmpty()) {
            throw new RuntimeException();
        }
        return new ResponseEntity<>(optionalTeam.get(), HttpStatus.OK);
    }

    @PostMapping("/team")
    public ResponseEntity saveTeamInfo(@RequestBody TeamRequest teamRequest) {
        Set<String> takenShortNames = teamInfoRepository.findAllShortNames();
        if (takenShortNames.contains(teamRequest.getShortName())) {
            throw new RuntimeException();
        }
        if (teamRequest.getShape() < 0 || teamRequest.getShape() > 100) {
            throw new RuntimeException();
        }
        teamInfoRepository.save(new TeamInfo(teamRequest.getShortName(), teamRequest.getFullName(), teamRequest.getShape()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<TeamInfo>> listTeamInfos(@RequestParam("groupName") Optional<String> groupName) {
        System.out.format("[TeamInfoController] listTeams groupName=%s", groupName);

        return groupName.map(s -> new ResponseEntity<>(teamInfoRepository.findAll(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(teamInfoRepository.findAll(), HttpStatus.OK));
    }

    // TODO: Bulk request
}
