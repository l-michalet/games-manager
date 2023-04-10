package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    private final GroupService groupService;
    private final TournamentRepository tournamentRepository;

    public TournamentService(GroupService groupService, TournamentRepository tournamentRepository) {
        this.groupService = groupService;
        this.tournamentRepository = tournamentRepository;
    }

    public void createTournament(String name, List<Integer> teamInfoIds, Integer nbOfGroups) {
        if (teamInfoIds == null) {
            System.out.println("Error, teamInfoIds is null");
            return;
        }
        Tournament tournament = tournamentRepository.save(new Tournament(name));
        System.out.println("**********************************\nGenerate groups:");
        groupService.prepareGroups(tournament.getId(), teamInfoIds, nbOfGroups);
    }


    //********* Play group stages
    public void playGroups() {
        System.out.println("**********************************\nPlay group stages:");
        groupService.playGroups();
    }

    public void getGroupResults(Integer tournamentId) {
        System.out.println("**********************************\nGroup stages results:");
        groupService.getGroupsResults(tournamentId);
    }
}