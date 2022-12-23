package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Game;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.model.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GameService gameService;
    private final TeamService teamService;
    private final ResultsService resultsService;

    private static final String BREAK = "BREAK";
    private static final int FIRST_ASCII_CHARACTER = 65;


    public void createGroups(int nbOfGroups, int nbOfTeamsPerGroup) {
        List<Team> allTeams = teamService.getAllTeams();
        int nbOfTeams = nbOfGroups * nbOfTeamsPerGroup;
        if (nbOfTeams > allTeams.size()) {
            System.out.format("groupService | Asked for too many teams: %d > %d \n", nbOfTeams, allTeams.size());
            System.exit(0);
        }

        Collections.shuffle(allTeams);

        List<Team> chosenTeams = new ArrayList<>();
        for (int i=0; i<nbOfGroups; i++) {
            List<Team> groupTeams = allTeams.subList(i*nbOfTeamsPerGroup, (i+1)*nbOfTeamsPerGroup);
            String groupName = Character.toString(i + FIRST_ASCII_CHARACTER); // A, B, C, ....
            groupTeams.forEach(t -> t.setGroupName(groupName));
            chosenTeams.addAll(groupTeams);
            System.out.format("Group %s: %s \n", groupName, groupTeams.stream().map(Team::getShortName).collect(Collectors.joining(" - ")));
        }

        teamService.saveAll(chosenTeams);
    }

    /////////////////////////////////

    public void scheduleGroups() {
        Map<String, List<String>> teamIdsPerGroup = teamService.allTeamIdsPerGroup();
        for (Map.Entry<String, List<String>> teamIdsOfGroup : teamIdsPerGroup.entrySet()) {
            System.out.println("\n**********************************");
            System.out.format("Group %s:\n", teamIdsOfGroup.getKey());
            scheduleGroup(teamIdsOfGroup.getKey(), teamIdsOfGroup.getValue());
        }
    }

    private void scheduleGroup(String groupName, List<String> teamIdsOfGroup) {
        int nbOfTeams = teamIdsOfGroup.size();
        List<String> evenTeamOfGroupIds = new ArrayList<>(teamIdsOfGroup.subList(1, nbOfTeams));

        if (nbOfTeams % 2 != 0) {
            evenTeamOfGroupIds.add(BREAK);
        }
        int nbOfRounds = evenTeamOfGroupIds.size();

        int day = 0;
        for (int round = nbOfRounds-1; round >= 0; round--)  {
            System.out.println("----- Day " + (++day) + " -----");
            int number = 0;
            int teamId = round % nbOfRounds;
            if (!evenTeamOfGroupIds.get(teamId).equals(BREAK)) {
                gameService.scheduleGame(teamIdsOfGroup.get(0), evenTeamOfGroupIds.get(teamId), groupName, day, ++number);
            }
            for (int i = 1; i < (nbOfRounds+1)/2; i++) {
                int firstTeam = (round + i) % nbOfRounds;
                int secondTeam = (round  + nbOfRounds - i) % nbOfRounds;
                if (!evenTeamOfGroupIds.get(firstTeam).equals(BREAK) && !evenTeamOfGroupIds.get(secondTeam).equals(BREAK)) {
                    gameService.scheduleGame(evenTeamOfGroupIds.get(firstTeam), evenTeamOfGroupIds.get(secondTeam), groupName, day, ++number);
                }
            }
        }
    }


    /////////////////////////////////

    public void playGroups() {
        Map<Integer, List<Game>> gamesPerDay = gameService.allGamesPerDay();
        for (Map.Entry<Integer, List<Game>> gamesOfDay : gamesPerDay.entrySet()) {
            this.playGroupDay(gamesOfDay.getKey(), gamesOfDay.getValue());
        }
    }

    private void playGroupDay(Integer day, List<Game> gamesOfDay) {
        System.out.println("\n**********************************");
        System.out.format("Day %d:\n", day);

        Map<String, List<Game>> gamesOfDayPerGroup = gamesOfDay.stream()
                .collect(Collectors.groupingBy(Game::getGroupName));
        for (Map.Entry<String, List<Game>> group : gamesOfDayPerGroup.entrySet()) {
            System.out.println("\n----------------------");
            System.out.format("Group %s: \n", group.getKey());
            gameService.playGames(group.getValue());
        }
    }


    /////////////////////////////////
    public void getGroupsResults() {
        Map<String, List<String>> teamIdsPerGroup = teamService.allTeamIdsPerGroup();
        for (Map.Entry<String, List<String>> teamIdsOfGroup : teamIdsPerGroup.entrySet()) {
            System.out.println("\n**********************************");
            System.out.format("Group %s:\n", teamIdsOfGroup.getKey());
            getGroupResults(teamIdsOfGroup.getValue());
        }
    }

    private void getGroupResults(List<String> teamIdsOfGroup) {
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
