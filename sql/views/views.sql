DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE IF NOT EXISTS students
(
    id   serial PRIMARY KEY,
    name VARCHAR(50)
);
INSERT INTO students (name)
VALUES ('Иван Иванов');
INSERT INTO students (name)
VALUES ('Петр Петров');

CREATE TABLE IF NOT EXISTS authors
(
    id   serial PRIMARY KEY,
    name VARCHAR(50)
);
INSERT INTO authors (name)
VALUES ('Александр Пушкин');
INSERT INTO authors (name)
VALUES ('Николай Гоголь');

CREATE TABLE IF NOT EXISTS books
(
    id        serial PRIMARY KEY,
    name      VARCHAR(200),
    author_id INTEGER REFERENCES authors (id)
);

INSERT INTO books (name, author_id)
VALUES ('Евгений Онегин', 1);
INSERT INTO books (name, author_id)
VALUES ('Капитанская дочка', 1);
INSERT INTO books (name, author_id)
VALUES ('Дубровский', 1);
INSERT INTO books (name, author_id)
VALUES ('Мертвые души', 2);
INSERT INTO books (name, author_id)
VALUES ('Вий', 2);

CREATE TABLE IF NOT EXISTS orders
(
    id         serial PRIMARY KEY,
    active     boolean DEFAULT TRUE,
    book_id    INTEGER REFERENCES books (id),
    student_id INTEGER REFERENCES students (id)
);

INSERT INTO orders (book_id, student_id)
VALUES (1, 1);
INSERT INTO orders (book_id, student_id)
VALUES (3, 1);
INSERT INTO orders (book_id, student_id)
VALUES (5, 2);
INSERT INTO orders (book_id, student_id)
VALUES (4, 1);
INSERT INTO orders (book_id, student_id)
VALUES (2, 2);

-- Получить список книг самого популярного автора у студентов (популярный - это больше всего книг автора на руках).
SELECT b.name
FROM books AS b
WHERE b.author_id = (SELECT a.id
                     FROM orders AS o
                              JOIN books b ON o.book_id = b.id
                              JOIN authors a ON b.author_id = a.id
                     GROUP BY (a.id)
                     ORDER BY COUNT(a.id) DESC
                     LIMIT 1);

-- Убить представление если такое уже существует.
DROP VIEW IF EXISTS show_all_books_of_popular_author;

-- Создать представление VIEW
CREATE VIEW show_all_books_of_popular_author
AS
(
SELECT b.name
FROM books AS b
WHERE b.author_id = (SELECT a.id
                     FROM orders AS o
                              JOIN books b ON o.book_id = b.id
                              JOIN authors a ON b.author_id = a.id
                     GROUP BY (a.id)
                     ORDER BY COUNT(a.id) DESC
                     LIMIT 1)
    );

-- Воспользоваться представлением.
SELECT *
FROM show_all_books_of_popular_author;