package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.request.TournamentRequest;
import com.meritis.gamesmanager.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TeamService teamService;
    private final GroupPhaseService groupPhaseService;

    public TournamentService(TournamentRepository tournamentRepository, TeamService teamService, GroupPhaseService groupPhaseService) {
        this.tournamentRepository = tournamentRepository;
        this.teamService = teamService;
        this.groupPhaseService = groupPhaseService;
    }

    @Transactional
    public Tournament createTournament(TournamentRequest tournamentRequest) {
        List<Team> teams = tournamentRequest.getTeamIds().stream()
                .map(teamService::getTeamById)
                .collect(Collectors.toList());

        Tournament tournament = new Tournament(
                tournamentRequest.getName(),
                tournamentRequest.getStartDate(),
                tournamentRequest.getEndDate(),
                tournamentRequest.getNbOfGroups(),
                tournamentRequest.isDirectElimination(),
                teams
        );
        Tournament savedTournament = tournamentRepository.save(tournament);

        // Add the tournament to the teams
        teams.forEach(t -> t.getTournaments().add(savedTournament));
        teamService.saveAll(teams);

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
        return tournamentRepository.save(tournament);
    }

    public void deleteTournament(Long tournamentId) {
        tournamentRepository.deleteById(tournamentId);

        //TODO: delete groups, games, ....
    }

    @Transactional
    public Tournament startTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament with id " + tournamentId + " not found"));

        // Create group phases and assign teams to groups
        groupPhaseService.createGroupPhase(tournament);
        tournament.setStartDate(LocalDate.now());
        tournamentRepository.save(tournament);
        return tournament;
    }

}
