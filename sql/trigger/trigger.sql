DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS history_of_price CASCADE;

CREATE TABLE IF NOT EXISTS products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

-- функция начисления налогов + 13%
CREATE OR REPLACE FUNCTION tax()
    RETURNS TRIGGER AS
$$
BEGIN
    UPDATE products
    SET price = price + price * 0.13
    WHERE id = (SELECT id FROM inserted);
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';

--  1)  Триггер должен срабатывать после вставки данных, для любого товара
--  и просто насчитывать налог на товар (нужно прибавить налог к цене товара).
--  Действовать он должен не на каждый ряд, а на запрос (statement уровень)
CREATE TRIGGER tax_trigger
    AFTER INSERT
    ON products
    REFERENCING NEW TABLE AS INSERTED
    FOR EACH STATEMENT
EXECUTE PROCEDURE tax();


--  Нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products,
--  будет заносить имя, цену и текущую дату в таблицу history_of_price.
CREATE TABLE history_of_price
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

-- функция заносит имя, цену и текущую дату в таблицу history_of_price
CREATE OR REPLACE FUNCTION save_history()
    RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO history_of_price
        (name, price, date)
    VALUES (NEW.name, NEW.price, now());
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';


CREATE TRIGGER save_history
    AFTER INSERT
    ON products
    REFERENCING NEW TABLE AS INSERTED
    FOR EACH ROW
EXECUTE PROCEDURE save_history();


-- проверка работы триггеров

INSERT INTO products (name, producer, count, price)
VALUES ('Computer', 'Dell', 10, 100);

INSERT INTO products (name, producer, count, price)
VALUES ('Computer', 'IBM', 5, 50);

INSERT INTO products (name, producer, count, price)
VALUES ('Computer', 'HP', 10, 300);

SELECT *
FROM products;

SELECT *
FROM history_of_price;
