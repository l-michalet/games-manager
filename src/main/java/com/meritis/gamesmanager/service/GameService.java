package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final ResultsService resultsService;

    private static final List<Integer> goalsDistribution = Arrays.asList(0,0,0,0,1,1,1,1,1,2,2,2,2,3,3,3,4,4,5);

    public List<Game> listGames(String groupName, Integer groupDay) {
        List<Game> games;
        if (groupName != null && groupDay != null) {
            games = gameRepository.findAllByGroupNameAndGroupDay(groupName,groupDay);
        } else if(groupName != null) {
            games = gameRepository.findAllByGroupName(groupName);
        } else if(groupDay != null) {
            games = gameRepository.findAllByGroupDay(groupDay);
        } else {
            games = gameRepository.findAll();
        }
        return games;
    }

    public void scheduleGame(int home, int away, String group, int day) {
        System.out.println(home + " vs. "+ away);

        Game game = new Game(home, away, group, day);
        gameRepository.save(game);
    }

    public Map<Integer, List<Game>> allGamesPerDay() {
        return gameRepository.findAll().stream()
                .filter(t -> t.getGroupDay() != 0)
                .collect(Collectors.groupingBy(Game::getGroupDay));
    }

    public void playGames(List<Game> games) {
        for (Game game : games) {
            this.playGame(game);
        }
    }

    public void playGame(Game game) {
        generateRandomScore(game);
        gameRepository.save(game);
        resultsService.updateResults(game);
        System.out.format(game.getHomeTeamId() + " %d : %d " + game.getAwayTeamId() +"\n", game.getHomeGoals(), game.getAwayGoals());
    }

    private void generateRandomScore(Game game){
        Random random = new Random();
        game.setHomeGoals(goalsDistribution.get(random.nextInt(goalsDistribution.size())));
        game.setAwayGoals(goalsDistribution.get(random.nextInt(goalsDistribution.size())));
    }
}
