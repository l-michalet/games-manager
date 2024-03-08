package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Round;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.request.TournamentRequest;
import com.meritis.gamesmanager.repository.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TeamService teamService;
    private final RoundService roundService;

    @Transactional
    public Tournament createTournament(TournamentRequest tournamentRequest) {
        List<Team> teams = teamService.getAllTeamsById(tournamentRequest.getTeamIds());
        Tournament tournament = new Tournament(
                tournamentRequest.getName(),
                tournamentRequest.getStartDate(),
                tournamentRequest.getEndDate(),
                teams
        );
        Tournament savedTournament = tournamentRepository.save(tournament);

        // Add the tournament to the teams
        teams.forEach(t -> t.getTournaments().add(savedTournament));
        teamService.saveAll(teams);

        return savedTournament;
    }

    public List<Tournament> listTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournamentById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found with id: " + tournamentId));
    }

    @Transactional
    public Tournament updateTournament(Long tournamentId, TournamentRequest tournamentRequest) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found with id: " + tournamentId));

        tournament.setName(tournamentRequest.getName() != null ? tournamentRequest.getName() : tournament.getName());
        tournament.setStartDate(tournamentRequest.getStartDate() != null ? tournamentRequest.getStartDate() : tournament.getStartDate());
        tournament.setEndDate(tournamentRequest.getEndDate() != null ? tournamentRequest.getEndDate() : tournament.getEndDate());

        Set<Long> tournamentTeamIds = tournament.getTeams()
                .stream()
                .map(Team::getId)
                .collect(Collectors.toSet());
        for (Long teamId : tournamentRequest.getTeamIds()) {
            if (!tournamentTeamIds.contains(teamId)) {
                tournament.getTeams().add(teamService.getTeamById(teamId));
            }
        }
        return tournamentRepository.save(tournament);
    }

    public void deleteTournament(Long tournamentId) {
        tournamentRepository.deleteById(tournamentId);

        //TODO: delete groups, games, ....
    }

    // Tournament actions

    @Transactional
    public void continueTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament with id " + tournamentId + " not found"));

        Round round = roundService.generateNewRound(tournament);

        if (round == null) {
            List<Team> teams = roundService.getRoundWinners(tournament.getCurrentRound());
            tournament.setWinner(teams.get(0));
        }

        tournament.setCurrentRound(round);
        tournamentRepository.save(tournament);
    }

    // Teams in tournament

    public List<Team> addTeamsToTournament(Long tournamentId, List<Long> teamIds) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament with id " + tournamentId + " not found"));
        List<Team> teams = teamService.getAllTeamsById(teamIds);
        if (teams.size() != teamIds.size()) {
            throw new EntityNotFoundException("Some teams have not been found");
        }
        tournament.getTeams().addAll(teams);
        tournamentRepository.save(tournament);
        return tournament.getTeams();
    }

    public List<Team> getTeamsInTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament with id " + tournamentId + " not found"));
        return tournament.getTeams();
    }

    public void removeTeamFromTournament(Long tournamentId, Long teamId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament with id " + tournamentId + " not found"));
        List<Team> teams = tournament.getTeams().stream()
                .filter(team -> team.getId().equals(teamId))
                .collect(Collectors.toList());
        tournament.setTeams(teams);
        tournamentRepository.save(tournament);
    }

}
