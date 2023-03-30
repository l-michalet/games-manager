package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.repository.GameRepository;
import com.meritis.gamesmanager.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GameApi {
    private final GameService gameService;
    private final GameRepository gameRepository;

    public GameApi(GameService gameService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> findGame(@PathVariable("gameId") Integer gameId) {
        System.out.format("GameApi | findGame gameId=%s", gameId);
        Game game = gameRepository.findById(gameId).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/game")
    public ResponseEntity<List<Game>> listGames(@RequestParam("groupName") String groupName,
                                                @RequestParam("groupDay") Integer groupDay) {
        System.out.format("GameApi | listGames");
        return new ResponseEntity<>(gameService.listGames(groupName, groupDay), HttpStatus.OK);
    }
}
