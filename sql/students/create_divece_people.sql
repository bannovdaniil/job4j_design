create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO devices(name, price) VALUES ('Dell server', 1999.99);
INSERT INTO devices(name, price) VALUES ('IBM computer', 100);
INSERT INTO devices(name, price) VALUES ('Apple iPad', 45.3);
INSERT INTO devices(name, price) VALUES ('Sumsung Galaxy', 1232.2);
INSERT INTO devices(name, price) VALUES ('Panasonic камера', 663.3);
INSERT INTO devices(name, price) VALUES ('Yandex Колонка', 999);
INSERT INTO devices(name, price) VALUES ('Mail phone', 1234.1);
INSERT INTO devices(name, price) VALUES ('Sber колонка', 481.44);
INSERT INTO devices(name, price) VALUES ('Yamaha синтизатор', 8712);
INSERT INTO devices(name, price) VALUES ('LG телевизор', 24394.2);

INSERT INTO people(name) VALUES ('Лана');
INSERT INTO people(name) VALUES ('Дана');
INSERT INTO people(name) VALUES ('Сережа');
INSERT INTO people(name) VALUES ('Жанна');
INSERT INTO people(name) VALUES ('Игнат');
INSERT INTO people(name) VALUES ('Соня');
INSERT INTO people(name) VALUES ('Боря');
INSERT INTO people(name) VALUES ('Рая');
INSERT INTO people(name) VALUES ('Гриша');
INSERT INTO people(name) VALUES ('Ульяна');

INSERT INTO devices_people(device_id, people_id ) VALUES (1, 2);
INSERT INTO devices_people(device_id, people_id ) VALUES (1, 3);
INSERT INTO devices_people(device_id, people_id ) VALUES (2, 2);
INSERT INTO devices_people(device_id, people_id ) VALUES (3, 2);
INSERT INTO devices_people(device_id, people_id ) VALUES (4, 2);
INSERT INTO devices_people(device_id, people_id ) VALUES (4, 1);
INSERT INTO devices_people(device_id, people_id ) VALUES (5, 2);
INSERT INTO devices_people(device_id, people_id ) VALUES (6, 3);
INSERT INTO devices_people(device_id, people_id ) VALUES (6, 1);
INSERT INTO devices_people(device_id, people_id ) VALUES (7, 5);
INSERT INTO devices_people(device_id, people_id ) VALUES (8, 4);
INSERT INTO devices_people(device_id, people_id ) VALUES (8, 7);
INSERT INTO devices_people(device_id, people_id ) VALUES (8, 6);
INSERT INTO devices_people(device_id, people_id ) VALUES (9, 8);
INSERT INTO devices_people(device_id, people_id ) VALUES (10, 9);
INSERT INTO devices_people(device_id, people_id ) VALUES (10, 10);

