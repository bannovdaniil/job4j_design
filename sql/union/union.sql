DROP TABLE IF EXISTS movie CASCADE;
DROP TABLE IF EXISTS book CASCADE;

CREATE TABLE movie
(
    id       SERIAL PRIMARY KEY,
    name     TEXT,
    director TEXT
);

CREATE TABLE book
(
    id     SERIAL PRIMARY KEY,
    title  TEXT,
    author TEXT
);

INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

-- выведите названия всех фильмов, которые сняты по книге;
SELECT name
FROM movie
INTERSECT
SELECT title
FROM book;

-- выведите все названия книг, у которых нет экранизации;
SELECT title
FROM book
EXCEPT
SELECT name
FROM movie;

-- выведите все уникальные названия произведений из таблиц movie и book (т.е фильмы, которые сняты не по книге, и книги без экранизации)
(SELECT name
 FROM movie
 EXCEPT
 SELECT title
 FROM book)
UNION ALL
(SELECT title
 FROM book
 EXCEPT
 SELECT name
 FROM movie);