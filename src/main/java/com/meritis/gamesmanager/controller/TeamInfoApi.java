package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.TeamInfo;
import com.meritis.gamesmanager.model.request.TeamRequest;
import com.meritis.gamesmanager.repository.TeamInfoRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TeamInfoApi {
    private final TeamInfoRepository teamInfoRepository;

    public TeamInfoApi(TeamInfoRepository teamInfoRepository) {
        this.teamInfoRepository = teamInfoRepository;
    }

    @GetMapping("/team")
    public ResponseEntity<TeamInfo> findTeamInfo(@RequestParam("shortName") String shortName) {
        System.out.format("TeamInfoApi | findTeamInfo shortName=%s", shortName);
        TeamInfo teamInfo = teamInfoRepository.findByShortName(shortName)
                .orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(teamInfo, HttpStatus.OK);
    }

    @PostMapping("/team")
    public ResponseEntity<Void> saveTeamInfo(@RequestBody TeamRequest teamRequest) {
        System.out.format("TeamInfoApi | saveTeamInfo shortName=%s", teamRequest.getShortName());
        Set<String> takenShortNames = teamInfoRepository.findAllShortNames();
        if (takenShortNames.contains(teamRequest.getShortName())) {
            throw new RuntimeException();
        }
        if (teamRequest.getShape() < 0 || teamRequest.getShape() > 100) {
            throw new RuntimeException();
        }
        teamInfoRepository.save(new TeamInfo(teamRequest.getShortName(), teamRequest.getFullName(), teamRequest.getShape()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/teams")
    public ResponseEntity<List<TeamInfo>> listTeamInfos(@Param("groupName") String groupName) {
        System.out.format("TeamInfoApi | listTeamInfos groupName=%s", groupName);
        return new ResponseEntity<>(teamInfoRepository.findAll(), HttpStatus.OK); //TODO: by groupName
    }

    // TODO: Bulk request
}
