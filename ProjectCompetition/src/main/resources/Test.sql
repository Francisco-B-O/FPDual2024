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
);


INSERT INTO roles (role_id,role_name, role_description) VALUES
(1,'ADMIN', 'Encargado de crear, eliminar y modificar torneos. Tiene permisos absolutos'),
(2,'MANAGER', 'Puede gestionar torneos a menor nivel, no se le permite crear, modificar o eliminarlos. Tiene algunos permisos'),
(3,'PLAYER', 'Usuario que solo se le permite entrar y salir de equipos y de torneos. Tiene los permisos básicos');


INSERT INTO users (user_id, user_nick, user_email, user_password, user_state)
VALUES (1, 'usuario1', '@Mail1', 'password1', 'CONNECTED'),
       (2, 'usuario2', '@Mail2', 'password2', 'CONNECTED');


INSERT INTO users_roles (users_roles_user_id, users_roles_role_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO modalities (modality_id, modality_number_players, modality_name)
VALUES (1, 5, 'Mod1'),
       (2, 10, 'Mod2');

INSERT INTO teams (team_id, team_name, team_logo, team_modality_id, team_captain_id)
VALUES (1, 'Equipo1', 'logo1', 1, 1),
       (2, 'Equipo2', 'logo2', 1, 2);

INSERT INTO users_teams (users_teams_user_id, users_teams_team_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO formats (format_id, format_name)
VALUES (1, 'Formato1'),
       (2, 'Formato2');

INSERT INTO tournaments (tournament_id, tournament_name, tournament_size, tournament_start_date, tournament_end_date, tournament_state, tournament_description, tournament_modality_id, tournament_format_id)
VALUES (1, 'Torneo1', 10, '2023-01-01 00:00:00', '2023-01-01 23:59:59', 'NOT_STARTED', 'Descripción del torneo 1', 1, 1),
       (2, 'Torneo2', 20, '2023-02-01 00:00:00', '2023-02-02 23:59:59', 'NOT_STARTED', 'Descripción del torneo 2', 2, 2);

INSERT INTO tournaments_teams (tournaments_teams_tournament_id, tournaments_teams_team_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2);
