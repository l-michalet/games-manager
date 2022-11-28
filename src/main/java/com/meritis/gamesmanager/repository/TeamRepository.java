package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Team;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class TeamRepository {

    private static final Map<String, Team> teams = new HashMap<>();
    static {
        Stream<Team> teamsStream = Stream.of(
            new Team("Brazil", 1, 80),
            new Team("Belgium", 2, 50),
            new Team("Argentina", 3, 30),
            new Team("France", 4, 100),
            new Team("England", 5, 60),
            new Team("Italy", 6, 20),
            new Team("Spain", 7, 80),
            new Team("Netherlands", 8, 50),
            new Team("Portugal", 9, 80),
            new Team("Denmark", 10, 50),
            new Team("Germany", 11, 30),
            new Team("Croatia", 12, 70),
            new Team("Mexico", 13, 40),
            new Team("Uruguay", 14, 80),
            new Team("Switzerland", 15, 50),
            new Team("United-States", 16, 40)
        );
        teams.putAll(teamsStream.collect(Collectors.toMap(Team::getName, Function.identity())));
    }

    public Map<String,Team> findAll() {
        return teams;
    }

    public Team findByName(String name) {
        return teams.get(name);
    }
}
