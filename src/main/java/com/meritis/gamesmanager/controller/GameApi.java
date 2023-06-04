package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class GameApi {
    private final GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> listGames() {
        log.info("GameApi | listGames");
        return new ResponseEntity<>(gameService.listGames(), HttpStatus.OK);
    }

}
