package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.model.Results;
import com.meritis.gamesmanager.repository.ResultsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultsService {
    private final ResultsRepository resultsRepository;

    @Transactional
    public void updateResults(Game game) {
        this.updateTeamResults(game.getHomeTeamId(), game.getHomeGoals(), game.getAwayGoals());
        this.updateTeamResults(game.getAwayTeamId(), game.getAwayGoals(), game.getHomeGoals());
    }

    private void updateTeamResults(int teamId, int goalsFor, int goalsAgainst) {
        Optional<Results> optionalResults = resultsRepository.findById(teamId);
        Results results;
        if (optionalResults.isEmpty()) {
            results = new Results(teamId);
        } else {
            results = optionalResults.get();
        }

        results.addGoalsFor(goalsFor);
        results.addGoalsAgainst(goalsAgainst);

        if (goalsFor - goalsAgainst > 0) {
            results.win();
        } else if(goalsFor - goalsAgainst < 0) {
            results.loss();
        } else {
            results.draw();
        }
        resultsRepository.save(results);
    }

    public List<Results> getGroupResults(List<Integer> teamIds) {
        List<Results> groupResults = resultsRepository.findAllById(teamIds);
        groupResults.sort(Results::compareTo);
        return groupResults;
    }
}
