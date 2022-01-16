CREATE TABLE type(
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) NOT NULL
);

CREATE TABLE product(
	id  SERIAL PRIMARY KEY, 
	name VARCHAR(255) NOT NULL, 
	type_id INTEGER REFERENCES type(id), 
	expired_date DATE, 
	price FLOAT
);

INSERT INTO type(name) VALUES('канцелярия'); -- 1
INSERT INTO type(name) VALUES('молоко');    -- 2
INSERT INTO type(name) VALUES('мороженое');    -- 3
INSERT INTO type(name) VALUES('СЫР');        -- 4
INSERT INTO type(name) VALUES('гаджеты');      -- 5
INSERT INTO type(name) VALUES('садоводство');--6
INSERT INTO type(name) VALUES('еда');        -- 7
INSERT INTO type(name) VALUES('детские товары');--8
INSERT INTO type(name) VALUES('инструмент'); -- 9
INSERT INTO type(name) VALUES('одежда'); --10

INSERT INTO product(name, type_id, expired_date, price) VALUES('Тетрадь', 1, date('2022-01-22'), 3.50);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Учебник', 1, date('2022-01-10'), 590);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Календарь', 1, date('2022-01-14'), 300);

INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко Лебедянь', 2, date('2022-01-10'), 90);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко Фермерское', 2, date('2022-01-11'), 100);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко Домик в деревне', 2, date('2022-02-01'), 89);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко Кот мотроскин', 2, date('2022-03-01'), 92);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко Конское', 2, date('2022-01-10'), 90);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко Козье', 2, date('2022-01-11'), 100);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко Валлио', 2, date('2022-02-01'), 89);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко От бабушки', 2, date('2022-03-01'), 92);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко От дедушки', 2, date('2022-01-10'), 90);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко Тёма', 2, date('2022-01-11'), 100);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко Молочная Станция', 2, date('2022-02-01'), 89);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоко Коровье', 2, date('2022-03-01'), 92);

INSERT INTO product(name, type_id, expired_date, price) VALUES('мороженое Лакомка', 3, date('2022-03-02'), 70);
INSERT INTO product(name, type_id, expired_date, price) VALUES('мороженое Умка', 3, date('2022-04-05'), 50);

INSERT INTO product(name, type_id, expired_date, price) VALUES('СЫР бондарский', 4, date('2022-09-01'), 800);
INSERT INTO product(name, type_id, expired_date, price) VALUES('СЫР адегейский', 4, date('2021-01-01'), 600);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Сыр голанский', 4, date('2023-01-01'), 700);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Сыр масдам', 4, date('2022-03-04'), 600);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Сыр ламбер', 4, date('2022-02-01'), 760);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Сыр гауда', 4, date('2022-11-21'), 590);

INSERT INTO product(name, type_id, expired_date, price) VALUES('Телефон', 5, date('2030-01-01'), 5000);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Телевизор', 5, date('2025-01-01'), 25000);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Наушники', 5, date('2050-01-01'), 500);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Лопата', 6, date('3022-01-01'), 1500);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Грабли', 6, date('3022-01-01'), 1000);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Качели', 6, date('2072-01-01'), 7000);

INSERT INTO product(name, type_id, expired_date, price) VALUES('Сок', 7, date('2022-05-01'), 150);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Конфеты', 7, date('2022-03-01'), 350);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Картошка', 7, date('2022-04-01'), 90);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Манты', 7, date('2023-03-01'), 350);

INSERT INTO product(name, type_id, expired_date, price) VALUES('Кукла', 8, date('2030-01-01'), 300);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Велосипед', 8, date('2030-01-01'), 3500);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Робот', 8, date('2035-01-01'), 500);

INSERT INTO product(name, type_id, expired_date, price) VALUES('Молоток', 9, date('3022-01-01'), 500);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Отвертка', 9, date('3022-01-01'), 200);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Шуруповерт', 9, date('2025-01-01'), 25000);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Шорты', 10, date('3022-01-01'), 1500);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Футболка', 10, date('2030-01-01'), 25000);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Рубашка', 10, date('2030-01-01'), 1500);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Носки', 10, date('2030-01-01'), 350);
INSERT INTO product(name, type_id, expired_date, price) VALUES('Кепка', 10, date('2030-01-01'), 400);

