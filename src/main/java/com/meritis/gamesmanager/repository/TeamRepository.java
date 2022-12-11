package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Team;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class TeamRepository {

    private static final Map<String, Team> teams = new HashMap<>();
    static {
        Stream<Team> teamsStream = Stream.of(
            new Team("BRA", "Brazil", 1, 50),
            new Team("BEL",  "Belgium", 2, 50),
            new Team("ARG","Argentina", 3, 70),
            new Team("FRA", "France", 4, 100),
            new Team("ENG", "England", 5, 60),
            new Team("ITA", "Italy", 6, 20),
            new Team("ESP", "Spain", 7, 80),
            new Team("NDL", "Netherlands", 8, 50),
            new Team("POR", "Portugal", 9, 80),
            new Team("DNK", "Denmark", 10, 50),
            new Team("DEU", "Germany", 11, 30),
            new Team("CRO", "Croatia", 12, 70),
            new Team("MEX", "Mexico", 13, 40),
            new Team("URY", "Uruguay", 14, 80),
            new Team("CHE", "Switzerland", 15, 50),
            new Team("USA", "United-States", 16, 40)
        );
        teams.putAll(teamsStream.collect(Collectors.toMap(Team::getShortName, Function.identity())));
    }

    public List<Team> findAll() {
        return new ArrayList<>(teams.values());
    }
}
