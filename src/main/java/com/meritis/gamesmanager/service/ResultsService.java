package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.model.Score;
import com.meritis.gamesmanager.model.TeamResults;
import com.meritis.gamesmanager.repository.TeamResultsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultsService {
    private final  TeamResultsRepository teamResultsRepository;

    public void updateResults(Game game) {
        Score score = game.getScore();
        this.updateTeamResults(game.getHome(), score.getHomeGoals(), score.getAwayGoals());
        this.updateTeamResults(game.getAway(), score.getAwayGoals(), score.getHomeGoals());
    }

    public void updateTeamResults(String teamId, int goalsFor, int goalsAgainst) {
        TeamResults teamResults = teamResultsRepository.findById(teamId);
        if (teamResults == null) {
            teamResults = new TeamResults(teamId);
        }

        teamResults.addGoalsFor(goalsFor);
        teamResults.addGoalsAgainst(goalsAgainst);

        if (goalsFor - goalsAgainst > 0) {
            teamResults.win();
        } else if(goalsFor - goalsAgainst < 0) {
            teamResults.loss();
        } else {
            teamResults.draw();
        }
        teamResultsRepository.save(teamResults);
    }

    public List<TeamResults> getGroupResults(List<String> teamIds) {
        List<TeamResults> groupResults = teamResultsRepository.findById(teamIds);
        groupResults.sort(TeamResults::compareTo);
        return groupResults;
    }
}
