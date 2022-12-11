package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.model.Score;
import com.meritis.gamesmanager.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final ResultsService resultsService;

    private static final List<Integer> goalsDistribution = Arrays.asList(0,0,0,0,1,1,1,1,1,2,2,2,2,3,3,3,4,4,5);

    public void scheduleGame(String gameId, String home, String away) {
        Game game = new Game(gameId, home, away);
        gameRepository.save(game);
    }

    public void playGames(List<String> gameIds) {
        List<Game> games = gameRepository.findById(gameIds);
        for (Game game : games) {
            this.playGame(game);
        }
    }

    public void playGame(Game game) {
        Score score = generateRandomScore();
        game.setScore(score);
        gameRepository.save(game);
        resultsService.updateResults(game);
        System.out.format(game.getHome() + " %d : %d " + game.getAway() +"\n", score.getHomeGoals(), score.getAwayGoals());
    }

    private Score generateRandomScore(){
        Random random = new Random();

        int homeGoals = goalsDistribution.get(random.nextInt(goalsDistribution.size()));
        int awayGoals = goalsDistribution.get(random.nextInt(goalsDistribution.size()));

        return new Score(homeGoals, awayGoals);
    }
}
