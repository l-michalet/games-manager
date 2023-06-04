CREATE TABLE tournaments (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    start_date DATE,
    end_date DATE,
    nb_of_groups INTEGER,
    is_direct_elimination BOOLEAN
);

CREATE TABLE teams (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    short_name VARCHAR(255),
    full_name VARCHAR(255)
);

CREATE TABLE tournaments_teams (
    tournament_id BIGINT,
    team_id BIGINT,
    PRIMARY KEY (tournament_id, team_id),
    FOREIGN KEY (tournament_id) REFERENCES tournaments(id),
    FOREIGN KEY (team_id) REFERENCES teams(id)
);

CREATE TABLE group_phases (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    tournament_id BIGINT,
    FOREIGN KEY (tournament_id) REFERENCES tournaments(id)
);

CREATE TABLE groups (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    group_name VARCHAR(255),
    group_phase_id BIGINT,
    FOREIGN KEY (group_phase_id) REFERENCES group_phases(id)
);

CREATE TABLE elimination_phases (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    tournament_id BIGINT,
    type VARCHAR(255),
    FOREIGN KEY (tournament_id) REFERENCES tournaments(id)
);

CREATE TABLE games (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    home_team BIGINT,
    away_team BIGINT,
    home_team_score INTEGER,
    away_team_score INTEGER,
    FOREIGN KEY (home_team) REFERENCES teams(id),
    FOREIGN KEY (away_team) REFERENCES teams(id)
);
