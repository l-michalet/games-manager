package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Group;
import com.meritis.gamesmanager.model.GroupPhase;
import com.meritis.gamesmanager.model.Tournament;
import com.meritis.gamesmanager.repository.GroupPhaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupPhaseService {

    private final GroupPhaseRepository groupPhaseRepository;
    private final GroupService groupService;

    public GroupPhase createGroupPhase(Tournament tournament) {
        GroupPhase groupPhase = new GroupPhase();
        groupPhase.setTournament(tournament);
        List<Group> groups = groupService.createGroups(tournament.getTeams(), tournament.getNbOfGroups());
        groupPhase.setGroups(groups);
        return groupPhaseRepository.save(groupPhase);
    }

}
