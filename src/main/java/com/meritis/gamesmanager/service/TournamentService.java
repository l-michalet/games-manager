package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.mappers.TournamentMapper;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.model.request.TournamentRequest;
import com.meritis.gamesmanager.model.response.TournamentResponse;
import com.meritis.gamesmanager.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
;import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TeamService teamService;

    public TournamentService(TournamentRepository tournamentRepository, TeamService teamService) {
        this.tournamentRepository = tournamentRepository;
        this.teamService = teamService;
    }

    @Transactional
    public TournamentResponse createTournament(TournamentRequest tournamentRequest) {
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
        return TournamentMapper.toResponse(savedTournament);
    }

}
