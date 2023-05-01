package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final GroupService groupService;
    private final TournamentRepository tournamentRepository;

    @Transactional
    public void createTournament(String name, List<Integer> teamInfoIds, int nbOfGroups) {
        if (teamInfoIds == null) {
            System.out.println("Error, teamInfoIds is null");
            return;
        }
        Tournament tournament = new Tournament(name);
        System.out.println("**********************************\nGenerate groups:");
        groupService.prepareGroups(tournament.getId(), teamInfoIds, nbOfGroups);
        tournamentRepository.save(new Tournament(name));
    }

}
