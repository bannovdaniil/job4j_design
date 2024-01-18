DROP TABLE IF EXISTS books CASCADE;

CREATE TABLE IF NOT EXISTS books
(
    id        serial PRIMARY KEY,
    name      VARCHAR(200),
    author_id INTEGER REFERENCES authors (id)
);

BEGIN TRANSACTION;

INSERT INTO books (name, author_id)
VALUES ('Евгений Онегин', 1);
INSERT INTO books (name, author_id)
VALUES ('Капитанская дочка', 1);
INSERT INTO books (name, author_id)
VALUES ('Дубровский', 1);

SAVEPOINT first;
INSERT INTO books (name, author_id)
VALUES ('Мертвые души', 2);
INSERT INTO books (name, author_id)
VALUES ('Вий', 2);

UPDATE books SET name = 'Update name' WHERE id = 1;
SELECT * FROM books;

SELECT * FROM books;
rollback to first;

SELECT * FROM books;

SAVEPOINT second_point;

DELETE FROM books WHERE id <> 0;
SELECT * FROM books;

ROLLBACK to second_point;
SELECT * FROM books;

COMMIT;