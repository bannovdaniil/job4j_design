DROP TABLE IF EXISTS books CASCADE;

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

begin transaction isolation level SERIALIZABLE;
select sum(author_id) from books;
update books set name = 'New book 2' where id = 3;
select * from books;
commit transaction;

begin transaction isolation level SERIALIZABLE;
update books set name = 'Памятник Seriazable 2' where id = 2;
select avg(author_id) from books;
select * from books;
commit transaction;
