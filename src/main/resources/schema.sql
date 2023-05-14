CREATE TABLE tournament (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

CREATE TABLE team (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE tournament_team (
    id BIGINT NOT NULL PRIMARY KEY,
    tournament_id BIGINT NOT NULL,
    team_id BIGINT NOT NULL,
    FOREIGN KEY (tournament_id) REFERENCES tournament(id),
    FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE group (
    id BIGINT NOT NULL PRIMARY KEY,
    tournament_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    number_of_teams INT NOT NULL,
    FOREIGN KEY (tournament_id) REFERENCES tournament(id)
);

CREATE TABLE group_team (
    id BIGINT NOT NULL PRIMARY KEY,
    group_id BIGINT NOT NULL,
    team_id BIGINT NOT NULL,
    FOREIGN KEY (group_id) REFERENCES group(id),
    FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE group_game (
    id BIGINT NOT NULL PRIMARY KEY,
    group_id BIGINT NOT NULL,
    home_team_id BIGINT NOT NULL,
    away_team_id BIGINT NOT NULL,
    home_team_score INT,
    away_team_score INT,
    FOREIGN KEY (group_id) REFERENCES group(id),
    FOREIGN KEY (home_team_id) REFERENCES team(id),
    FOREIGN KEY (away_team_id) REFERENCES team(id)
);

CREATE TABLE elimination_game (
    id BIGINT NOT NULL PRIMARY KEY,
    tournament_id BIGINT NOT NULL,
    round INT NOT NULL,
    game_number INT NOT NULL,
    home_team_id BIGINT NOT NULL,
    away_team_id BIGINT NOT NULL,
    home_team_score INT,
    away_team_score INT,
    FOREIGN KEY (tournament_id) REFERENCES tournament(id),
    FOREIGN KEY (home_team_id) REFERENCES team(id),
    FOREIGN KEY (away_team_id) REFERENCES team(id)
);
