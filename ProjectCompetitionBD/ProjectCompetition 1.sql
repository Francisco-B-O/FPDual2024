DROP DATABASE IF EXISTS ProjectCompetition;
CREATE DATABASE ProjectCompetition CHARSET utf8mb4;
USE ProjectCompetition;

CREATE TABLE users (
    user_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_nick VARCHAR(20) NOT NULL UNIQUE,
    user_email VARCHAR(50) UNIQUE NOT NULL,
    user_password VARCHAR(256) NOT NULL,
    user_avatar LONGTEXT,
    user_state ENUM('DISCONNECTED', 'DISABLED', 'CONNECTED') NOT NULL
);

CREATE TABLE roles (
    role_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL UNIQUE,
    role_description VARCHAR(256) NOT NULL
);

CREATE TABLE users_roles (
    users_roles_user_id INT UNSIGNED NOT NULL,
    users_roles_role_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (users_roles_user_id , users_roles_role_id),
    CONSTRAINT fk_userId_users_roles FOREIGN KEY (users_roles_user_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_roleId_users_roles FOREIGN KEY (users_roles_role_id)
        REFERENCES roles (role_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE modalities (
    modality_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    modality_number_players INT UNSIGNED NOT NULL,
    modality_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE teams (
    team_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(20) NOT NULL,
    team_logo LONGTEXT,
    team_modality_id INT UNSIGNED NOT NULL,
    team_captain_id INT UNSIGNED NOT NULL,
    UNIQUE (team_modality_id , team_name),
    CONSTRAINT fk_modalityId_teams FOREIGN KEY (team_modality_id)
        REFERENCES modalities (modality_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_userCaptainId_teams FOREIGN KEY (team_captain_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE users_teams (
    users_teams_user_id INT UNSIGNED NOT NULL,
    users_teams_team_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (users_teams_user_id , users_teams_team_id),
    CONSTRAINT fk_userId_users_teams FOREIGN KEY (users_teams_user_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_teamId_users_teams FOREIGN KEY (users_teams_team_id)
        REFERENCES teams (team_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE formats (
    format_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    format_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE tournaments (
    tournament_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    tournament_name VARCHAR(20) NOT NULL UNIQUE,
    tournament_size INT NOT NULL,
    tournament_start_date DATETIME NOT NULL,
    tournament_end_date DATETIME NOT NULL,
    tournament_state ENUM('NOT_STARTED', 'IN_GAME', 'FINALIZED', 'SUSPENDED', 'POSTPONED') NOT NULL,
    tournament_logo LONGTEXT,
    tournament_description VARCHAR(256) NOT NULL,
    tournament_modality_id INT UNSIGNED NOT NULL,
    tournament_format_id INT UNSIGNED NOT NULL,
    CONSTRAINT fk_modalityId_tournaments FOREIGN KEY (tournament_modality_id)
        REFERENCES modalities (modality_id),
    CONSTRAINT fk_formatId_tournaments FOREIGN KEY (tournament_format_id)
        REFERENCES formats (format_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tournaments_teams (
    tournaments_teams_tournament_id INT UNSIGNED NOT NULL,
    tournaments_teams_team_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (tournaments_teams_tournament_id , tournaments_teams_team_id),
    CONSTRAINT fk_tournamentId_inscriptions FOREIGN KEY (tournaments_teams_tournament_id)
        REFERENCES tournaments (tournament_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_teamId_inscriptions FOREIGN KEY (tournaments_teams_team_id)
        REFERENCES teams (team_id)
        ON DELETE CASCADE ON UPDATE CASCADE
)

