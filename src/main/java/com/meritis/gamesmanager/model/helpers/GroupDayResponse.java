package com.meritis.gamesmanager.model.helpers;

import com.meritis.gamesmanager.model.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDayResponse {
    int day;
    List<Game> games;
}
