package com.meritis.gamesmanager.model.helpers;

import com.meritis.gamesmanager.model.TeamInfo;

import java.util.List;

public class GroupResponse {
    public String name;
    public List<TeamInfo> teamInfos;

    public GroupResponse(String name, List<TeamInfo> teamInfos) {
        this.name = name;
        this.teamInfos = teamInfos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TeamInfo> getTeamInfos() {
        return teamInfos;
    }

    public void setTeamInfos(List<TeamInfo> teamInfos) {
        this.teamInfos = teamInfos;
    }
}
