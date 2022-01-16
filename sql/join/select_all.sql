CREATE TABLE games(
    games_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE players(
    players_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    rang INTEGER,
    games_id INTEGER REFERENCES games(games_id)
);

INSERT INTO games(name) VALUES('WarCraft');
INSERT INTO games(name) VALUES('StarCraft');
INSERT INTO games(name) VALUES('DOTA');
INSERT INTO games(name) VALUES('WarOfTanks');

INSERT INTO players(name, rang, games_id) VALUES('One', 1, 1);
INSERT INTO players(name, rang, games_id) VALUES('Two', 2, 1);
INSERT INTO players(name, rang, games_id) VALUES('Three', 3, 2);
INSERT INTO players(name, rang, games_id) VALUES('Foure', 7, 2);
INSERT INTO players(name, rang, games_id) VALUES('Five', 1, 1);
INSERT INTO players(name, rang, games_id) VALUES('Six', 5, 4);
INSERT INTO players(name, rang, games_id) VALUES('Seven', 10, 3);
INSERT INTO players(name, rang, games_id) VALUES('Eight', 8, 3);
INSERT INTO players(name, rang, games_id) VALUES('Nine', 9, 4);
INSERT INTO players(name, rang, games_id) VALUES('Elfe', 80, 1);

SELECT * FROM games as g
INNER JOIN players as p
ON g.games_id = p.games_id;

SELECT * FROM players as p
INNER JOIN games as g
ON g.games_id = p.games_id;

SELECT * FROM games as g
INNER JOIN players as p
ON g.games_id = p.games_id
WHERE g.name LIKE '%War%';

SELECT * FROM players as p
INNER JOIN games as g
ON p.rang>=80 AND p.games_id = g.games_id;
