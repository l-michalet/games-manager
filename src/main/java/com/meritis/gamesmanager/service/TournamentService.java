package com.meritis.gamesmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final GroupService groupService;

    //********* Prepare group stages
    public void createGroups(int nbOfGroups, int nbOfTeamsPerGroup) {
        System.out.println("**********************************\nGenerated groups:");
        groupService.createGroups(nbOfGroups, nbOfTeamsPerGroup);
    }

    public void scheduleGroups() {
        System.out.println("**********************************\nGenerated groups schedules:");
        groupService.scheduleGroups();
    }

    //********* Play group stages
    public void playGroups() {
        System.out.println("**********************************\nPlay group stages:");
        groupService.playGroups();
    }

    public void getGroupResults() {
        System.out.println("**********************************\nGroup stages results:");
        groupService.getGroupsResults();
    }
}
