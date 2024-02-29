USE ProjectCompetition;

INSERT INTO users (user_nick, user_email, user_password, user_avatar, user_state) VALUES
('Nick01', 'user01@example.com',SHA2('asd123SA?¿', 256), '', 'Desconectado'),
('Nick02', 'user02@example.com',SHA2('Pasdfsd213¿?', 256), '', 'Desactivado'),
('Nick03', 'user03@example.com',SHA2('345dfSAW!@', 256), '', 'Conectado'),
('Nick04', 'user04@example.com',SHA2('Password123@!', 256), '', 'Desconectado'),
('Nick05', 'user05@example.com',SHA2('dsad_234SB', 256), '', 'Conectado');

INSERT INTO roles (role_name, role_description) VALUES
('Administrador', 'Encargado de crear, eliminar y modificar torneos. Tiene permisos absolutos'),
('Gestor', 'Puede gestionar torneos a menor nivel, no se le permite crear, modificar o eliminarlos. Tiene algunos permisos'),
('Jugador', 'Usuario que solo se le permite entrar y salir de equipos y de torneos. Tiene los permisos básicos');


INSERT INTO users_roles (users_roles_user_id, users_roles_role_id) VALUES
(1,1),
(1,2),
(1,3),
(2,1),
(3,2),
(3,3),
(4,1),
(4,2),
(5,2);

INSERT INTO modalities (modality_number_players, modality_name) VALUES
(2,'Futbolín'),
(3,'3x3');

INSERT INTO teams (team_name, team_logo, team_modality_id,team_captain_id) VALUES
('Team01', '', 1,1),
('Team02', '', 2,2),
('Team03', '', 2,3),
('Team04', '', 1,4),
('Team05', '', 2,5);

INSERT INTO users_teams (users_teams_user_id, users_teams_team_id) VALUES
(1,2),
(1,5),
(2,2),
(3,1),
(3,2),
(4,3),
(4,4),
(5,2);

INSERT INTO formats (format_name) VALUES
('Liguilla'),
('KO'),
('PlayOff');

INSERT INTO tournaments (tournament_name, tournament_size, tournament_start_date, 
						 tournament_end_date, tournament_state, tournament_logo, 
						 tournament_description, tournament_modality_id, tournament_format_id) VALUES
('Tournament01', 20, CURRENT_DATE()+3, '2023-02-25', 'EN_JUEGO', '', 'Liga de Futbolín de empresa', 1, 1),
('Tournament02', 40, '2023-03-23', '2023-03-30', 'EN_JUEGO', '', 'Torneo de Futbolín de empresa', 1, 2),
('Tournament03', 32, '2022-07-31', '2022-08-09', 'EN_JUEGO', '', 'Torneo de 3x3', 2, 3),
('Tournament04', 10, CURRENT_DATE(), '2023-02-25', 'EN_JUEGO', '', 'Liga 3x3', 2, 1),
('Tournament05', 20, CURRENT_DATE(), '2023-06-20', 'EN_JUEGO', '', 'Torneo de Futbolín de empresa', 1, 2);

INSERT INTO tournaments_teams (tournaments_teams_tournament_id, tournaments_teams_team_id) VALUES
(1,1),
(1,5),
(2,3),
(3,3),
(3,4),
(4,1),
(4,2),
(5,3),
(5,4);


