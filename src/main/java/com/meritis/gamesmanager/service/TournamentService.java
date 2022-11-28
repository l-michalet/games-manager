package com.meritis.gamesmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final GroupService groupService;

    //********* Prepare group stages
    public void createGroupStage(int nbOfGroups, int nbOfTeamsPerGroup) {
        System.out.println("**********************************\nGenerated groups:");
        groupService.createGroupStage(nbOfGroups, nbOfTeamsPerGroup);
    }

    public void scheduleGroupStage() {
        System.out.println("**********************************\nGenerated groups schedules:");
        groupService.scheduleGroupStage();
    }

//    //********* Play group stages
//    public void playGroupStage() {
//        System.out.println("**********************************\nPlay group stages:");
//        groupService.playGroupStage();
//    }
}
