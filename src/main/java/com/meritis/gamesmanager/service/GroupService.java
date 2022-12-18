package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Group;
import com.meritis.gamesmanager.model.GroupDay;
import com.meritis.gamesmanager.model.TeamResults;
import com.meritis.gamesmanager.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    private final GameService gameService;
    private final TeamService teamService;
    private final ResultsService resultsService;

    private static final String BREAK = "BREAK";
    private static final int FIRST_ASCII_CHARACTER = 65;


    public void createGroups(int nbOfGroups, int nbOfTeamsPerGroup) {
        List<String> allTeamIds = teamService.getAllTeamIds();
        int nbOfTeams = nbOfGroups * nbOfTeamsPerGroup;
        if (nbOfTeams > allTeamIds.size()) {
            System.out.format("tournamentService | Asked for too many teams: %d > %d \n", nbOfTeams, allTeamIds.size());
            System.exit(0);
        }

        Collections.shuffle(allTeamIds);

        for (int i=0; i<nbOfGroups; i++) {
            List<String> teamIdsInGroup = allTeamIds.subList(i*nbOfTeamsPerGroup, (i+1)*nbOfTeamsPerGroup);
            String groupName = Character.toString(i + FIRST_ASCII_CHARACTER); // A, B, C, ....
            groupRepository.save(new Group(groupName, teamIdsInGroup));
            System.out.format("Group %s: %s \n", groupName, String.join(" - ", teamIdsInGroup));
        }
    }

    /////////////////////////////////

    public void scheduleGroups() {
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            System.out.println("\n**********************************");
            System.out.format("Group %s:\n", group.getName());
            scheduleGroup(group);
        }
    }

    private void scheduleGroup(Group group) {
        List<String> teamIds = group.getTeamIds();
        int nbOfTeams = teamIds.size();
        List<String> evenTeamIds = new ArrayList<>(teamIds.subList(1, nbOfTeams));

        if (nbOfTeams % 2 != 0) {
            evenTeamIds.add(BREAK);
        }
        int nbOfRounds = evenTeamIds.size();

        List<GroupDay> schedule = new ArrayList<>();

        int day = 0;
        for (int round = nbOfRounds-1; round >= 0; round--)  {
            System.out.println("----- Day " + (++day) + " -----");
            List<String> gamesOfDay = new ArrayList<>();
            int count = 0;
            int teamId = round % nbOfRounds;
            if (!evenTeamIds.get(teamId).equals(BREAK)) {
                System.out.println(teamIds.get(0) + " vs. "+ evenTeamIds.get(teamId));
                String gameId = group.getName() + day + (++count);
                gameService.scheduleGame(gameId, teamIds.get(0), evenTeamIds.get(teamId));
                gamesOfDay.add(gameId);
            }
            for (int i = 1; i < (nbOfRounds+1)/2; i++) {
                int firstTeam = (round + i) % nbOfRounds;
                int secondTeam = (round  + nbOfRounds - i) % nbOfRounds;
                if (!evenTeamIds.get(firstTeam).equals(BREAK) && !evenTeamIds.get(secondTeam).equals(BREAK)) {
                    System.out.println(evenTeamIds.get(firstTeam) + " vs. "+ evenTeamIds.get(secondTeam));
                    String gameId = group.getName() + day + (++count);
                    gameService.scheduleGame(gameId, evenTeamIds.get(firstTeam), evenTeamIds.get(secondTeam));
                    gamesOfDay.add(gameId);
                }
            }
            schedule.add(new GroupDay(day, gamesOfDay));
        }
        group.setGroupDays(schedule);
        groupRepository.save(group);
    }


    /////////////////////////////////

    public void playGroups() {
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            System.out.println("\n**********************************");
            System.out.format("Group %s :\n", group.getName());
            this.playGroup(group.getGroupDays());
        }
    }

    private void playGroup(List<GroupDay> groupDays) {
        for (GroupDay groupDay : groupDays) {
            System.out.println("\n----------------------");
            System.out.format("Day %d: \n", groupDay.getDay());
            gameService.playGames(groupDay.getGamesOfDay());
        }
    }


    /////////////////////////////////
    public void getGroupsResults() {
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            System.out.println("\n**********************************");
            System.out.format("Group %s :", group.getName());
            this.getGroupResults(group);
        }
    }

    private void getGroupResults(Group group) {
        List<TeamResults> groupResults = resultsService.getGroupResults(group.getTeamIds());
        System.out.println("\n-------------------------------------------");
        System.out.println("| Team | MP | W | D | L | GF | GA | GD || PTS");
        System.out.println("-------------------------------------------");
        for (int i=0; i < groupResults.size(); i++) {
            System.out.println("| " + (i+1) + " - " +  groupResults.get(i).toString());
        }
        System.out.println("-------------------------------------------");
    }
}
