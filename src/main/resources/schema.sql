CREATE TABLE IF NOT EXISTS TEAM (
     short_name VARCHAR(128) PRIMARY KEY,
     full_name VARCHAR(128) NOT NULL,
     fifa_rank  INTEGER NOT NULL,
     shape INTEGER NOT NULL,
     group_name VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS GAME (
    id VARCHAR(16) PRIMARY KEY,
    home_team VARCHAR(128) NOT NULL,
    away_team VARCHAR(128) NOT NULL,
    group_name VARCHAR(16),
    group_day INTEGER,
    home_goals INTEGER,
    away_goals INTEGER
);

CREATE TABLE IF NOT EXISTS RESULTS (
    team_name VARCHAR(128) PRIMARY KEY,
    games_played INTEGER NOT NULL,
    wins INTEGER NOT NULL,
    draws INTEGER NOT NULL,
    losses INTEGER NOT NULL,
    points INTEGER NOT NULL,
    goals_for INTEGER NOT NULL,
    goals_against INTEGER NOT NULL,
    goals_diff INTEGER NOT NULL
);