DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE IF NOT EXISTS products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

INSERT INTO products (name, producer, count, price)
VALUES ('Computer', 'Dell', 10, 100);

INSERT INTO products (name, producer, count, price)
VALUES ('Computer', 'IBM', 5, 50);

INSERT INTO products (name, producer, count, price)
VALUES ('Computer', 'IRUS', 0, 52);

INSERT INTO products (name, producer, count, price)
VALUES ('Computer', 'Samsung', 0, 10);


INSERT INTO products (name, producer, count, price)
VALUES ('Computer', 'HP', 10, 300);

-- За основу возьмите таблицу, с которой мы работали в описании.
-- В описании мы рассмотрели вариант вставки данных, изменения данных.
-- Добавьте процедуру и функцию, которая будет удалять записи.
-- Условия выбирайте сами – удаление по id, удалить если количество товара равно 0 и т.п.

-- удалить запись по ID
CREATE
    OR REPLACE PROCEDURE delete_data(u_id INTEGER)
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    DELETE FROM products WHERE id = u_id;
END
$$;

-- удалить запись по ID и если удалось удалить то вернуть TRUE, иначе FALSE
CREATE
    OR REPLACE FUNCTION delete_if_zero(u_id INTEGER)
    RETURNS BOOLEAN
    LANGUAGE 'plpgsql'
AS
$$
DECLARE
    count  INTEGER;
    result BOOLEAN DEFAULT FALSE;
BEGIN
    SELECT INTO count p.count FROM products AS p WHERE id = u_id;
    IF count = 0 THEN
        DELETE FROM products WHERE id = u_id;
        SELECT INTO result EXISTS(SELECT id FROM products WHERE id = u_id);
    END IF;
    RETURN result;
END

$$;


CALL delete_data(1);
SELECT delete_if_zero(2);
SELECT delete_if_zero(3);

SELECT *
FROM products;

