package com.meritis.gamesmanager.model.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDaysResponse {
    String groupName;
    List<GroupDayResponse> groupDayResponses;
}
