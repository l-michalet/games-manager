package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final GroupService groupService;
    private final TournamentRepository tournamentRepository;

    public void createTournament(String name, int nbOfGroups, int nbOfTeamsPerGroup) {
        Tournament tournament = new Tournament(name);
        System.out.println("**********************************\nGenerate groups:");
        groupService.prepareGroups(tournament.getId(), nbOfGroups, nbOfTeamsPerGroup);
        tournamentRepository.save(new Tournament(name));
    }


    //********* Play group stages
    public void playGroups() {
        System.out.println("**********************************\nPlay group stages:");
        groupService.playGroups();
    }

    public void getGroupResults(int tournamentId) {
        System.out.println("**********************************\nGroup stages results:");
        groupService.getGroupsResults(tournamentId);
    }
}
