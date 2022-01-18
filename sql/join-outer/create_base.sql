CREATE TABLE departments (
  dep_id SERIAL PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE emploers (
  emp_id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  dep_id INTEGER REFERENCES departments(dep_id)
);

INSERT INTO departments(name) VALUES('Финансовый'); -- 1
INSERT INTO departments(name) VALUES('Информационный'); -- 2
INSERT INTO departments(name) VALUES('Юридический'); -- 3
INSERT INTO departments(name) VALUES('Маркетиг'); -- 4
INSERT INTO departments(name) VALUES('Продажи'); -- 5
INSERT INTO departments(name) VALUES('Транспортный'); -- 6
INSERT INTO departments(name) VALUES('Технический'); -- 7

INSERT INTO emploers(name, dep_id) VALUES('Марина', 1);
INSERT INTO emploers(name, dep_id) VALUES('Наташа', 1);
INSERT INTO emploers(name, dep_id) VALUES('Сережа', 2);
INSERT INTO emploers(name, dep_id) VALUES('Никита', 2);
INSERT INTO emploers(name, dep_id) VALUES('Коля', 4);
INSERT INTO emploers(name, dep_id) VALUES('Света', 4);
INSERT INTO emploers(name, dep_id) VALUES('Юля', 5);
INSERT INTO emploers(name, dep_id) VALUES('Софья', 5);
INSERT INTO emploers(name, dep_id) VALUES('Костя', 6);
INSERT INTO emploers(name, dep_id) VALUES('Алексей', 6);
INSERT INTO emploers(name, dep_id) VALUES('Гена', 6);
INSERT INTO emploers(name, dep_id) VALUES('Андрей', 7);

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. 
-- Используя cross join составить все возможные разнополые пары
CREATE TABLE teens (
	id SERIAL PRIMARY KEY,
    name VARCHAR(255),
	gender VARCHAR(255)
);                  

INSERT INTO teens(name, gender) VALUES('Саша', 'мальчик');
INSERT INTO teens(name, gender) VALUES('Женя', 'девочка');
INSERT INTO teens(name, gender) VALUES('Лена', 'девочка');
