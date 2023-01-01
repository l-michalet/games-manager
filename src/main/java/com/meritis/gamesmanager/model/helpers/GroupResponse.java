package com.meritis.gamesmanager.model.helpers;

import com.meritis.gamesmanager.model.TeamInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    String name;
    List<TeamInfo> teamInfos;
}
