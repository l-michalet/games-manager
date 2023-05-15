package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Group;
import com.meritis.gamesmanager.model.GroupGame;
import com.meritis.gamesmanager.model.GroupPhase;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.repository.GroupPhaseRepository;
import com.meritis.gamesmanager.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupPhaseService {

    private final GroupPhaseRepository groupPhaseRepository;
    private final GroupService groupService;

    public GroupPhaseService(GroupPhaseRepository groupPhaseRepository, GroupService groupService) {
        this.groupPhaseRepository = groupPhaseRepository;
        this.groupService = groupService;
    }

    public GroupPhase createGroupPhase(Tournament tournament) {
        GroupPhase groupPhase = new GroupPhase();
        groupPhase.setTournament(tournament);
        List<Group> groups = groupService.createGroups(tournament.getTeams(), tournament.getNbOfGroups());
        groupPhase.setGroups(groups);
        return groupPhaseRepository.save(groupPhase);
    }

}
