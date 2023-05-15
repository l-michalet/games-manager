package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.Group;
import com.meritis.gamesmanager.model.GroupGame;
import com.meritis.gamesmanager.model.Team;
import com.meritis.gamesmanager.repository.GameRepository;
import com.meritis.gamesmanager.repository.GroupGameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupGameService {

    private final GroupGameRepository groupGameRepository;

    public GroupGameService(GroupGameRepository groupGameRepository) {
        this.groupGameRepository = groupGameRepository;
    }

    private static final Team BREAK = new Team();

    public List<GroupGame> createGroupGames(List<Team> teams) {
        List<GroupGame> allGames = new ArrayList<>();
        int nbOfTeams = teams.size();
        List<Team> evenTeams = new ArrayList<>(teams.subList(1, nbOfTeams));

        if (nbOfTeams % 2 != 0) {
            evenTeams.add(BREAK);
        }

        int nbOfRounds = evenTeams.size();
        int day = 0;
        for (int round = nbOfRounds-1; round >= 0; round--)  {

            System.out.println("----- Day " + (++day) + " -----");
            List<GroupGame> gamesOfDay = new ArrayList<>();
            int teamId = round % nbOfRounds;
            if (!evenTeams.get(teamId).equals(BREAK)) {
                GroupGame groupGame = new GroupGame(teams.get(0), evenTeams.get(teamId), day);
                gamesOfDay.add(groupGame);
            }
            for (int i = 1; i < (nbOfRounds+1)/2; i++) {
                int homePos = (round + i) % nbOfRounds;
                int awayPos = (round  + nbOfRounds - i) % nbOfRounds;
                if (!evenTeams.get(homePos).equals(BREAK) && !evenTeams.get(awayPos).equals(BREAK)) {
                    GroupGame groupGame = new GroupGame(evenTeams.get(homePos), evenTeams.get(awayPos), day);
                    gamesOfDay.add(groupGame);
                }
            }
            allGames.addAll(gamesOfDay);
        }
        return groupGameRepository.saveAll(allGames);
    }
}
