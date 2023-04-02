package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.repository.GameRepository;
import com.meritis.gamesmanager.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(produces = "application/json", value = "Operations to manage games")
public class GameApi {
    private final GameService gameService;
    private final GameRepository gameRepository;

    public GameApi(GameService gameService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/games/{gameId}")
    @ApiOperation(value = "Find a game by id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found the game"),
            @ApiResponse(code = 404, message = "Game not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Game> findGame(@PathVariable("gameId") Integer gameId) {
        System.out.format("GameApi | findGame gameId=%s", gameId);
        Game game = gameRepository.findById(gameId).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/games")
    @ApiOperation(value = "List all the games", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the games"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<Game>> listGames(@RequestParam(required = false) String groupName,
                                                @RequestParam(required = false) Integer groupDay) {
        System.out.format("GameApi | listGames");
        return new ResponseEntity<>(gameService.listGames(groupName, groupDay), HttpStatus.OK);
    }

    @PostMapping("/games")
    @ApiOperation(value = "Create a friendly game", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the game"),
            @ApiResponse(code = 404, message = "Successfully created the game"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Game> createGame(@RequestParam String homeTeamShortName,
                                                @RequestParam String awayTeamShortName) {
        System.out.format("GameApi | create game homeTeamShortName=%s awayTeamShortName=%s", homeTeamShortName, awayTeamShortName);
        return new ResponseEntity<>(gameService.scheduleGame(homeTeamShortName, awayTeamShortName), HttpStatus.OK);
    }
}
