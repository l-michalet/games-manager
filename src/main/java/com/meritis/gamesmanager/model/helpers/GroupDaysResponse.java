package com.meritis.gamesmanager.model.helpers;

import java.util.List;

public class GroupDaysResponse {
    public String groupName;
    public List<GroupDayResponse> groupDayResponses;

    public GroupDaysResponse(String groupName, List<GroupDayResponse> groupDayResponses) {
        this.groupName = groupName;
        this.groupDayResponses = groupDayResponses;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<GroupDayResponse> getGroupDayResponses() {
        return groupDayResponses;
    }

    public void setGroupDayResponses(List<GroupDayResponse> groupDayResponses) {
        this.groupDayResponses = groupDayResponses;
    }
}
