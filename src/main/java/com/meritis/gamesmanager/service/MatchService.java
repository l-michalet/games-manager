package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Match;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.request.MatchEndedRequest;
import com.meritis.gamesmanager.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public Match createMatch(Team homeTeam, Team awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        return matchRepository.save(match);
    }

    public void deleteMatchById(Long id) {
        matchRepository.deleteById(id);
    }

    public void playMatch(Long matchId) {
        // Logique pour jouer un match
        // Cela peut inclure la mise à jour du statut du match, des scores, etc.
    }

    public void finishMatch(Long matchId, MatchEndedRequest matchEndedRequest) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Match not found with id: " + matchId));
        match.setHomeScore(matchEndedRequest.getHomeScore());
        match.setAwayScore(matchEndedRequest.getAwayScore());
        match.setEnded(true);
        matchRepository.save(match);
    }

}

