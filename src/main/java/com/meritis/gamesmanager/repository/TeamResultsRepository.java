package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.TeamResults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TeamResultsRepository {

    private static final Map<String, TeamResults> allTeamResults = new HashMap<>();

    public TeamResults findById(String id) {
        return allTeamResults.get(id);
    }

    public List<TeamResults> findById(List<String> ids) {
        List<TeamResults> teamResults = new ArrayList<>();
        for (String id : ids) {
            if (allTeamResults.containsKey(id)) {
                teamResults.add(allTeamResults.get(id));
            }
        }
        return teamResults;
    }

    public void save(TeamResults teamResults) {
        allTeamResults.put(teamResults.getShortName(), teamResults);
    }
}
