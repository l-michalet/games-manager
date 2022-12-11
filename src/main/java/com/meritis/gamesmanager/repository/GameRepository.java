package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GameRepository {
    private static final Map<String, Game> allGames = new HashMap<>();

    public List<Game> findById(List<String> ids) {
        List<Game> games = new ArrayList<>();
        for (String id : ids) {
            if (allGames.containsKey(id)) {
                games.add(allGames.get(id));
            }
        }
        return games;
    }

    public void save(Game game) {
        allGames.put(game.getId(), game);
    }
}
