package com.meritis.gamesmanager.model;

public class Tournament {

    private Integer id;
    private String name;

    public Tournament() {

    }

    public Tournament(String name) {
        this.name = name;
    }
    public Tournament(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}