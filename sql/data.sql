INSERT INTO account (id, fullname, mail, password) VALUES ('34212039-c1a5-4c03-a2a8-f8b541d3396d', 'Test Test', 'test.test@test.fr', '$2a$10$Rilms.2AHZXZ0CO2RW9hxuHy9Yl4eksJiz7fjUAIavvsJ3u5PPU2K');

INSERT INTO difficulty (id, level, zoom) VALUES ('1', 'Facile', 18);
INSERT INTO difficulty (id, level, zoom) VALUES ('2', 'Moyen', 15);
INSERT INTO difficulty (id, level, zoom) VALUES ('3', 'Expert', 13);

INSERT INTO distance (id, distance, points) VALUES ('1', 10, 5);
INSERT INTO distance (id, distance, points) VALUES ('2', 20, 3);
INSERT INTO distance (id, distance, points) VALUES ('3', 30, 1);

INSERT INTO multiplier (id, multiplier, time) VALUES ('1', 4, 5);
INSERT INTO multiplier (id, multiplier, time) VALUES ('2', 2, 10);
INSERT INTO multiplier (id, multiplier, time) VALUES ('3', 1, 20);

INSERT INTO difficulty_distance (difficulty_id, distance_id) VALUES ('1', '1');
INSERT INTO difficulty_distance (difficulty_id, distance_id) VALUES ('1', '2');
INSERT INTO difficulty_distance (difficulty_id, distance_id) VALUES ('1', '3');
INSERT INTO difficulty_distance (difficulty_id, distance_id) VALUES ('2', '1');
INSERT INTO difficulty_distance (difficulty_id, distance_id) VALUES ('2', '2');
INSERT INTO difficulty_distance (difficulty_id, distance_id) VALUES ('2', '3');
INSERT INTO difficulty_distance (difficulty_id, distance_id) VALUES ('3', '1');
INSERT INTO difficulty_distance (difficulty_id, distance_id) VALUES ('3', '2');
INSERT INTO difficulty_distance (difficulty_id, distance_id) VALUES ('3', '3');

INSERT INTO difficulty_multiplier (difficulty_id, multiplier_id) VALUES ('1', '1');
INSERT INTO difficulty_multiplier (difficulty_id, multiplier_id) VALUES ('1', '2');
INSERT INTO difficulty_multiplier (difficulty_id, multiplier_id) VALUES ('1', '3');
INSERT INTO difficulty_multiplier (difficulty_id, multiplier_id) VALUES ('2', '1');
INSERT INTO difficulty_multiplier (difficulty_id, multiplier_id) VALUES ('2', '2');
INSERT INTO difficulty_multiplier (difficulty_id, multiplier_id) VALUES ('2', '3');
INSERT INTO difficulty_multiplier (difficulty_id, multiplier_id) VALUES ('3', '1');
INSERT INTO difficulty_multiplier (difficulty_id, multiplier_id) VALUES ('3', '2');
INSERT INTO difficulty_multiplier (difficulty_id, multiplier_id) VALUES ('3', '3');
