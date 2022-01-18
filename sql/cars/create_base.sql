CREATE TABLE carbody(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);
CREATE TABLE engine(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);
CREATE TABLE gearbox(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);
CREATE TABLE cars(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	carbody_id INTEGER REFERENCES carbody(id),
	engine_id INTEGER REFERENCES engine(id),
	gearbox_id INTEGER REFERENCES gearbox(id)
);
                              --    1          2             3         4            5              6
INSERT INTO carbody(name) VALUES('седан'), ('пикап'), ('кабриолет'), ('купе'), ('универсал'), ('Хэтчбек');
INSERT INTO engine(name) VALUES('бензиновый'), ('дизельный'), ('газовый'), ('электрический');
INSERT INTO gearbox(name) VALUES('механическая'), ('автоматическая'), ('роботизированная'), ('вариативная');

INSERT INTO 
cars(name, carbody_id, engine_id, gearbox_id)
VALUES
('тойота',   1, 1, 1),
('мерседес', 3, 1, 2),
('бмв',      5, 2, 4),
('тесла',    1, 4, 4),
('ягуар',    1, 2, 4),
('хонда',    2, 2, 4),
('мустанг',  3, 2, 4),
('киа',      1, 1, 1),
('хюндай',   1, 2 ,4);


