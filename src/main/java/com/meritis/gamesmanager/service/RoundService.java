package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Match;
import com.meritis.gamesmanager.model.Round;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.repository.RoundRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoundService {

    private final RoundRepository roundRepository;
    private final MatchService matchService;

    public List<Round> getAllRounds() {
        return roundRepository.findAll();
    }

    public Round getRoundById(Long id) {
        return roundRepository.findById(id).orElse(null);
    }

    public Round saveRound(Round round) {
        return roundRepository.save(round);
    }

    public void deleteRoundById(Long id) {
        roundRepository.deleteById(id);
    }

    public void completeRound(Round round) {
        if (round.isCompleted()) {
            throw new RuntimeException();
        }
        for (Match match : round.getMatches()) {
            if (!match.isEnded()) {
                throw new RuntimeException();
            }
        }
        round.setCompleted(true);
        roundRepository.save(round);
    }

    public Round generateNewRound(Tournament tournament) {
        Round lastRound = tournament.getCurrentRound();

        // Start tournament
        if (lastRound == null) {
            return generateNewRound(1, tournament.getTeams());
        }

        this.completeRound(lastRound);
        List<Team> winners = getRoundWinners(tournament.getCurrentRound());
        return generateNewRound(lastRound.getRoundNumber() + 1, winners);
    }

    private Round generateNewRound(int roundNumber, List<Team> teams) {
        if (teams.size() == 1) {
            return null;
        }
        List<Match> matches = new ArrayList<>();

        Collections.shuffle(teams);
        for (int i=1; i < teams.size(); i++) {
            Match match = matchService.createMatch(teams.get(i-1), teams.get(i));
            matches.add(match);
        } //TODO: IMPROVE

        return new Round(roundNumber, matches);
    }

    public List<Team> getRoundWinners(Round round) {
        return round.getMatches().stream()
                .map(Match::getWinner)
                .collect(Collectors.toList());
    }
}

