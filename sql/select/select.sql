-- 9. Подзапросы [#504874]
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE customers
(
    id         SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    age        INT,
    country    TEXT
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES ('Ivan', 'Ivanov', 18, 'Bolivia'); -- 1
INSERT INTO customers (first_name, last_name, age, country)
VALUES ('Luka', 'Lukahenka', 24, 'Polha'); -- 2
INSERT INTO customers (first_name, last_name, age, country)
VALUES ('Fedr', 'Strelech', 18, 'Ruminia'); -- 3
INSERT INTO customers (first_name, last_name, age, country)
VALUES ('Olga', 'Ivolga', 81, 'Italy'); -- 4
INSERT INTO customers (first_name, last_name, age, country)
VALUES ('Oleg', 'Onapenko', 33, 'Egypet'); -- 5

SELECT *
FROM customers
WHERE age = (SELECT MIN(age) FROM customers);

CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    amount      INT,
    customer_id INT REFERENCES customers (id)
);

INSERT INTO orders (amount, customer_id)
VALUES (40, 1);
INSERT INTO orders (amount, customer_id)
VALUES (400, 1);
INSERT INTO orders (amount, customer_id)
VALUES (50, 2);
INSERT INTO orders (amount, customer_id)
VALUES (330, 4);
INSERT INTO orders (amount, customer_id)
VALUES (30, 4);
INSERT INTO orders (amount, customer_id)
VALUES (50, 1);


-- заказы для 1, 2, и 4 пользователя, вернуть должен 3 и 5
SELECT *
FROM customers
WHERE id NOT IN (SELECT DISTINCT orders.customer_id FROM orders);
