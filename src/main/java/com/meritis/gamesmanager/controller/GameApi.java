package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.GroupGame;
import com.meritis.gamesmanager.repository.GameRepository;
import com.meritis.gamesmanager.service.GroupGameService2;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GameApi {
    private final GroupGameService2 groupGameService2;
    private final GameRepository gameRepository;

    public GameApi(GroupGameService2 groupGameService2, GameRepository gameRepository) {
        this.groupGameService2 = groupGameService2;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<GroupGame> findGame(@PathVariable("gameId") Integer gameId) {
        System.out.format("GameApi | findGame gameId=%s", gameId);
        GroupGame groupGame = gameRepository.findById(gameId)
                .orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(groupGame, HttpStatus.OK);
    }

    @GetMapping("/game")
    public ResponseEntity<List<GroupGame>> listGames(@Param("groupName") String groupName,
                                                     @Param("groupDay") Integer groupDay) {
        System.out.format("GameApi | listGames");
        return new ResponseEntity<>(groupGameService2.listGames(groupName, groupDay), HttpStatus.OK);
    }
}
