package com.meritis.gamesmanager.model.request;

import lombok.Data;

@Data
public class TeamRequest {
    private String shortName;
    private String fullName;
    private int shape;
}
