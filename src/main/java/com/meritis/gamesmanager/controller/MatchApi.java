package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.request.MatchEndedRequest;
import com.meritis.gamesmanager.service.MatchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/matches")
public class MatchApi {

    private final MatchService matchService;

    @PostMapping("/{matchId}/end")
    public ResponseEntity<String> finishMatch(@PathVariable Long matchId, @RequestBody MatchEndedRequest matchEndedRequest) {
        matchService.finishMatch(matchId, matchEndedRequest);
        return ResponseEntity.ok("Match finished successfully.");
    }
}
