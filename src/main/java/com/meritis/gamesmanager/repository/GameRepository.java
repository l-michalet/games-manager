package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository {

    void save(Game game);

    List<Game> findAll();
}
