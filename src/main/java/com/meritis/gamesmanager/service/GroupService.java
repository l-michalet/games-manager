package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.model.Group;
import com.meritis.gamesmanager.model.GroupGame;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupGameService groupGameService;

    private static final int FIRST_ASCII_CHARACTER = 65;

    public GroupService(GroupRepository groupRepository, GroupGameService groupGameService) {
        this.groupRepository = groupRepository;
        this.groupGameService = groupGameService;
    }

    public List<Group> createGroups(List<Team> teams, int nbOfGroups) {
        int nbOfTeamsPerGroup = teams.size() / nbOfGroups;
        Collections.shuffle(teams);

        List<Group> groups = new ArrayList<>();
        for (int i=0; i<nbOfGroups; i++) {
            String groupName = Character.toString(i + FIRST_ASCII_CHARACTER); // Prepare groupName (A, B, C, ...)
            List<Team> groupTeams = teams.subList(i * nbOfTeamsPerGroup, (i+1) * nbOfTeamsPerGroup); // Collect teams for the group
            List<GroupGame> groupGames = groupGameService.createGroupGames(groupTeams);
            groups.add(new Group(groupName, groupTeams, groupGames));
        }
        return groupRepository.saveAll(groups);
    }
}
