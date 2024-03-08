package com.meritis.gamesmanager.model.request;

import lombok.Data;

@Data
public class MatchEndedRequest {

    private int homeScore;
    private int awayScore;

}
