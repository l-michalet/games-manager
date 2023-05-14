package com.meritis.gamesmanager.service;

import com.meritis.gamesmanager.model.GroupGame;
import com.meritis.gamesmanager.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupGameService {
    private final GameRepository gameRepository;

    private static final List<Integer> goalsDistribution = Arrays.asList(0,0,0,0,1,1,1,1,1,2,2,2,2,3,3,3,4,4,5);

    public List<GroupGame> listGames(String groupName, Integer groupDay) {
        List<GroupGame> groupGames;
        if (groupName != null && groupDay != null) {
            groupGames = gameRepository.findAllByGroupNameAndGroupDay(groupName,groupDay);
        } else if(groupName != null) {
            groupGames = gameRepository.findAllByGroupName(groupName);
        } else if(groupDay != null) {
            groupGames = gameRepository.findAllByGroupDay(groupDay);
        } else {
            groupGames = gameRepository.findAll();
        }
        return groupGames;
    }

    public void scheduleGame(int home, int away, String group, int day) {
        System.out.println(home + " vs. "+ away);

        GroupGame groupGame = new GroupGame(home, away, group, day);
        gameRepository.save(groupGame);
    }

    public Map<Integer, List<GroupGame>> allGamesPerDay() {
        return gameRepository.findAll().stream()
                .filter(t -> t.getGroupDay() != 0)
                .collect(Collectors.groupingBy(GroupGame::getGroupDay));
    }

    @Transactional
    public void playGames(List<GroupGame> groupGames) {
        for (GroupGame groupGame : groupGames) {
            this.playGame(groupGame);
        }
    }

    private void playGame(GroupGame groupGame) {
        generateRandomScore(groupGame);
        gameRepository.save(groupGame);
        System.out.format(groupGame.getHomeTeamId() + " %d : %d " + groupGame.getAwayTeamId() +"\n", groupGame.getHomeGoals(), groupGame.getAwayGoals());
    }

    private void generateRandomScore(GroupGame groupGame){
        Random random = new Random();
        groupGame.setHomeGoals(goalsDistribution.get(random.nextInt(goalsDistribution.size())));
        groupGame.setAwayGoals(goalsDistribution.get(random.nextInt(goalsDistribution.size())));
    }
}
