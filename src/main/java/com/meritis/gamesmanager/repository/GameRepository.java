package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameRepository {
    private static final List<Game> games = new ArrayList<>();
}
