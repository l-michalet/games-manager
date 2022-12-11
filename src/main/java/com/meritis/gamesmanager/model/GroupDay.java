package com.meritis.gamesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GroupDay {
    private int day;
    private List<String> gamesOfDay;
}
