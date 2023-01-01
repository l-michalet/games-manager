CREATE SEQUENCE hibernate_sequence;

CREATE SEQUENCE team_info_id_seq;
CREATE TABLE IF NOT EXISTS TEAM_INFO (
     id INTEGER PRIMARY KEY,
     short_name VARCHAR(128) NOT NULL,
     full_name VARCHAR(128) NOT NULL,
     fifa_rank INTEGER,
     shape INTEGER NOT NULL
);

CREATE SEQUENCE team_id_seq;
CREATE TABLE IF NOT EXISTS TEAM (
    id INTEGER PRIMARY KEY,
    team_info_id INTEGER NOT NULL,
    tournament_id INTEGER NOT NULL,
    group_name VARCHAR(16)
);

CREATE SEQUENCE game_id_seq;
CREATE TABLE IF NOT EXISTS GAME (
    id INTEGER PRIMARY KEY,
    home_team_id INTEGER NOT NULL,
    away_team_id INTEGER NOT NULL,
    group_name VARCHAR(16),
    group_day INTEGER,
    home_goals INTEGER,
    away_goals INTEGER
);

CREATE TABLE IF NOT EXISTS RESULTS (
    team_id INTEGER PRIMARY KEY,
    games_played INTEGER NOT NULL,
    wins INTEGER NOT NULL,
    draws INTEGER NOT NULL,
    losses INTEGER NOT NULL,
    points INTEGER NOT NULL,
    goals_for INTEGER NOT NULL,
    goals_against INTEGER NOT NULL,
    goals_diff INTEGER NOT NULL
);

CREATE SEQUENCE tournament_id_seq;
CREATE TABLE IF NOT EXISTS TOURNAMENT (
    id INTEGER PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);