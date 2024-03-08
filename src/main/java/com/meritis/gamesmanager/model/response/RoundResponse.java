package com.meritis.gamesmanager.model.response;

import com.meritis.gamesmanager.model.Match;
import lombok.Data;

import java.util.List;

@Data
public class RoundResponse {

    private Long id;
    private int roundNumber;
    private List<Match> matches;
    private boolean completed;

}