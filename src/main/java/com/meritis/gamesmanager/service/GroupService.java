package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.GroupGame;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.TeamInfo;
import com.meritis.gamesmanager.model.request.GroupResponse;
import com.meritis.gamesmanager.model.request.GroupDayResponse;
import com.meritis.gamesmanager.model.request.GroupDaysResponse;
import com.meritis.gamesmanager.model.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupGameService groupGameService;
    private final TeamService teamService;
    private final TeamInfoService teamInfoService;
    private final ResultsService resultsService;

    private static final Integer BREAK = -1;
    private static final int FIRST_ASCII_CHARACTER = 65;


    @Transactional
    public void prepareGroups(int tournamentId, List<Integer> teamInfoIds, int nbOfGroups) {
        this.createGroups(tournamentId, teamInfoIds, nbOfGroups);
        this.scheduleGroups(tournamentId);
    }

    private List<GroupResponse> createGroups(int tournamentId, List<Integer> teamInfoIds, int nbOfGroups) {
        int nbOfTeamsPerGroup = teamInfoIds.size() / nbOfGroups;

        Collections.shuffle(teamInfoIds);

        List<GroupResponse> groupResponses = new ArrayList<>();
        List<Team> chosenTeams = new ArrayList<>();
        for (int i=0; i<nbOfGroups; i++) {
            // Prepare groupName (A, B, C, ...)
            String groupName = Character.toString(i + FIRST_ASCII_CHARACTER);

            // Collect teams for the group
            List<TeamInfo> groupTeamInfos = teamInfoService.getTeamInfos(teamInfoIds).subList(i*nbOfTeamsPerGroup, (i+1)*nbOfTeamsPerGroup);
            for (TeamInfo groupTeamInfo : groupTeamInfos) {
                chosenTeams.add(new Team(groupTeamInfo.getId(), tournamentId, groupName));
            }

            // Prepare response
            groupResponses.add(new GroupResponse(groupName, groupTeamInfos));
            System.out.format("Group %s: %s \n", groupName, groupTeamInfos.stream().map(TeamInfo::getShortName).collect(Collectors.joining(" - ")));
        }

        teamService.saveAll(chosenTeams);
        return groupResponses;
    }

    /////////////////////////////////

    private List<GroupDaysResponse> scheduleGroups(int tournamentId) {
        Map<String, List<Integer>> teamIdsPerGroup = teamService.allTeamIdsPerGroup(tournamentId);
        List<GroupDaysResponse> allResponseGroupDays = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> teamIdsOfGroup : teamIdsPerGroup.entrySet()) {
            System.out.println("\n**********************************");
            System.out.format("Group %s:\n", teamIdsOfGroup.getKey());
            GroupDaysResponse groupDaysResponse = scheduleGroup(teamIdsOfGroup.getKey(), teamIdsOfGroup.getValue());
            allResponseGroupDays.add(groupDaysResponse);
        }
        return allResponseGroupDays;
    }

    private GroupDaysResponse scheduleGroup(String groupName, List<Integer> teamIdsOfGroup) {
        int nbOfTeams = teamIdsOfGroup.size();
        List<Integer> evenTeamOfGroupIds = new ArrayList<>(teamIdsOfGroup.subList(1, nbOfTeams));

        if (nbOfTeams % 2 != 0) {
            evenTeamOfGroupIds.add(BREAK);
        }
        int nbOfRounds = evenTeamOfGroupIds.size();

        List<GroupDayResponse> groupDayResponses = new ArrayList<>();
        int day = 0;
        for (int round = nbOfRounds-1; round >= 0; round--)  {
            System.out.println("----- Day " + (++day) + " -----");
            List<GroupGame> gamesOfDay = new ArrayList<>();
            int teamId = round % nbOfRounds;
            if (!evenTeamOfGroupIds.get(teamId).equals(BREAK)) {
                groupGameService.scheduleGame(teamIdsOfGroup.get(0), evenTeamOfGroupIds.get(teamId), groupName, day);
                gamesOfDay.add(new GroupGame(teamIdsOfGroup.get(0), evenTeamOfGroupIds.get(teamId)));
            }
            for (int i = 1; i < (nbOfRounds+1)/2; i++) {
                int firstTeam = (round + i) % nbOfRounds;
                int secondTeam = (round  + nbOfRounds - i) % nbOfRounds;
                if (!evenTeamOfGroupIds.get(firstTeam).equals(BREAK) && !evenTeamOfGroupIds.get(secondTeam).equals(BREAK)) {
                    groupGameService.scheduleGame(evenTeamOfGroupIds.get(firstTeam), evenTeamOfGroupIds.get(secondTeam), groupName, day);
                    gamesOfDay.add(new GroupGame(evenTeamOfGroupIds.get(firstTeam), evenTeamOfGroupIds.get(secondTeam)));
                }
            }
            groupDayResponses.add(new GroupDayResponse(day, gamesOfDay));
        }
        return new GroupDaysResponse(groupName, groupDayResponses);
    }


    /////////////////////////////////

    @Transactional
    public void playGroups() {
        Map<Integer, List<GroupGame>> gamesPerDay = groupGameService.allGamesPerDay();
        for (Map.Entry<Integer, List<GroupGame>> gamesOfDay : gamesPerDay.entrySet()) {
            this.playGroupDay(gamesOfDay.getKey(), gamesOfDay.getValue());
        }
    }

    private void playGroupDay(Integer day, List<GroupGame> gamesOfDay) {
        System.out.println("\n**********************************");
        System.out.format("Day %d:\n", day);

        Map<String, List<GroupGame>> gamesOfDayPerGroup = gamesOfDay.stream()
                .collect(Collectors.groupingBy(GroupGame::getGroupName));
        for (Map.Entry<String, List<GroupGame>> group : gamesOfDayPerGroup.entrySet()) {
            System.out.println("\n----------------------");
            System.out.format("Group %s: \n", group.getKey());
            groupGameService.playGames(group.getValue());
        }
    }


    /////////////////////////////////
    public void getGroupsResults(int tournamentId) {
        Map<String, List<Integer>> teamIdsPerGroup = teamService.allTeamIdsPerGroup(tournamentId);
        for (Map.Entry<String, List<Integer>> teamIdsOfGroup : teamIdsPerGroup.entrySet()) {
            System.out.println("\n**********************************");
            System.out.format("Group %s:\n", teamIdsOfGroup.getKey());
            getGroupResults(teamIdsOfGroup.getValue());
        }
    }

    private void getGroupResults(List<Integer> teamIdsOfGroup) {
        List<Results> groupResults = resultsService.getGroupResults(teamIdsOfGroup);
        System.out.println("\n-------------------------------------------");
        System.out.println("| Team | MP | W | D | L | GF | GA | GD || PTS");
        System.out.println("-------------------------------------------");
        for (int i=0; i < groupResults.size(); i++) {
            System.out.println("| " + (i+1) + " - " +  groupResults.get(i).toString());
        }
        System.out.println("-------------------------------------------");
    }
}
