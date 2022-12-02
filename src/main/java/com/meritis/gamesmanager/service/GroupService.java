package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.model.Group;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.repository.GroupRepository;
import com.meritis.gamesmanager.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final TeamRepository teamRepository;
    private final GroupRepository groupRepository;
    private static final Team BREAK = new Team("BREAK");
    private static final int FIRST_ASCII_CHARACTER = 65;


    public void createGroups(int nbOfGroups, int nbOfTeamsPerGroup) {
        Map<String, Team> allTeams = teamRepository.findAll();
        int nbOfTeams = nbOfGroups * nbOfTeamsPerGroup;
        if (nbOfTeams > allTeams.size()) {
            System.out.format("tournamentService | Asked for too many teams: %d > %d \n", nbOfTeams, allTeams.size());
            System.exit(0);
        }

        List<Team> teams = new ArrayList<>(allTeams.values());
        Collections.shuffle(teams);

        for (int i=0; i<nbOfGroups; i++) {
            List<Team> teamsInGroup = teams.subList(i*nbOfTeamsPerGroup, (i+1)*nbOfTeamsPerGroup);
            String groupName = String.valueOf((char)(i + FIRST_ASCII_CHARACTER)); // A, B, C, ....
            groupRepository.save(new Group(groupName,teamsInGroup));
            String teamsList = teamsInGroup.stream().map(Team::getName).collect(Collectors.joining(" - "));
            System.out.format("Group %s: %s \n", groupName, teamsList);
        }
    }

    public void scheduleGroups() {
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            System.out.println("\n**********************************");
            System.out.format("Group %s:\n", group.getName());
            scheduleGroup(group);
        }
    }

    private void scheduleGroup(Group group) {
        List<Team> teams = group.getTeams();
        int numOfTeams = teams.size();
        List<Team> evenTeams = new ArrayList<>(teams.subList(1, numOfTeams));

        if (numOfTeams % 2 != 0) {
            evenTeams.add(BREAK);
        }
        int nbOfRounds = evenTeams.size();

        Map<Game, Integer> schedule = new HashMap<>();

        int count = 0;
        for (int round = nbOfRounds-1; round >= 0; round--)  {
            System.out.println("----- Game " + (++count) + " -----");
            int teamId = round % nbOfRounds;
            if (!evenTeams.get(teamId).equals(BREAK)) {
                System.out.println(teams.get(0).getName() + " vs. "+ evenTeams.get(teamId).getName());
                schedule.put(new Game(teams.get(0), evenTeams.get(teamId)), count);
            }
            for (int i = 1; i < (nbOfRounds+1)/2; i++) {
                int firstTeam = (round + i) % nbOfRounds;
                int secondTeam = (round  + nbOfRounds - i) % nbOfRounds;
                if (!evenTeams.get(firstTeam).equals(BREAK) && !evenTeams.get(secondTeam).equals(BREAK)) {
                    System.out.println(evenTeams.get(firstTeam).getName() + " vs. "+ evenTeams.get(secondTeam).getName());
                    schedule.put(new Game(evenTeams.get(firstTeam), evenTeams.get(secondTeam)), count);
                }
            }
        }
        group.setSchedule(schedule);
        groupRepository.save(group);
    }

//    public void playGroups() {
//        List<Group> groups = groupRepository.findAll();
//        List<Group, Map<Game, Integer>
//        for (Group group : groups) {
//            groupNumber++;
//            System.out.println("\n**********************************");
//            System.out.format("Group %s :\n", group.getName());
//            playGroup(group);
//        }
//    }
//
//    public void playGroup(Group group) {
//        Map<Game, Integer>
//        int groupNumber = 0;
//        for (Group group : groups) {
//            groupNumber++;
//            System.out.println("\n**********************************");
//            System.out.format("Group %d :\n", groupNumber);
//            scheduleGroup(group);
//        }
//    }
}
