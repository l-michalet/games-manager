package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.request.TournamentRequest;
import com.meritis.gamesmanager.model.response.TournamentResponse;
import com.meritis.gamesmanager.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
;import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TeamService teamService;

    public TournamentService(TournamentRepository tournamentRepository, TeamService teamService) {
        this.tournamentRepository = tournamentRepository;
        this.teamService = teamService;
    }

    @Transactional
    public Tournament createTournament(TournamentRequest tournamentRequest) {
        List<Team> teams = new ArrayList<>();
        for (Long teamId : tournamentRequest.getTeamIds()) {
            teams.add(teamService.getTeamById(teamId));
        }
        Tournament tournament = new Tournament(
                tournamentRequest.getName(),
                tournamentRequest.getStartDate(),
                tournamentRequest.getEndDate(),
                tournamentRequest.isDirectElimination(),
                teams
        );

        Tournament savedTournament = tournamentRepository.save(tournament);
        return savedTournament;
    }

    public List<Tournament> getAllTournaments() {
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
        tournament.setDirectElimination(tournamentRequest.isDirectElimination()); // TODO: pb here

        Set<Long> tournamentTeamIds = tournament.getTeams().stream().map(Team::getId).collect(Collectors.toSet());
        for (Long teamId : tournamentRequest.getTeamIds()) {
            if (!tournamentTeamIds.contains(teamId)) {
                tournament.getTeams().add(teamService.getTeamById(teamId));
            }
        }

        Tournament updatedTournament = tournamentRepository.save(tournament);

        return updatedTournament;
    }

    public void deleteTournament(Long tournamentId) {
        tournamentRepository.deleteById(tournamentId);

        //TODO: delete groups, games, ....
    }

}
