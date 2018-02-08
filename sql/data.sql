INSERT INTO serie (id, city, description, lat, lng, name) VALUES ('0d855116-b665-4f98-9b98-5502b470d84a', 'Nancy', 'Test', 48.8, 6.6, 'Nancy');

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