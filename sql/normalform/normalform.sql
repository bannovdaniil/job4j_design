-- приведение базы к 3 нормальной форме
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS places CASCADE;
DROP TABLE IF EXISTS films CASCADE;
DROP TABLE IF EXISTS rentals CASCADE;

CREATE TABLE places
(
    place_id    SERIAL PRIMARY KEY,
    street_name VARCHAR(255),
    house       VARCHAR(100),
    apartment   VARCHAR(100)
);

CREATE TABLE users
(
    user_id  SERIAL PRIMARY KEY,
    name     VARCHAR(255),
    surname  VARCHAR(255),
    sex      VARCHAR(5),
    place_id INT REFERENCES places (place_id)
);

CREATE TABLE films
(
    film_id SERIAL PRIMARY KEY,
    name    VARCHAR(255)
);

CREATE TABLE rentals
(
    id      SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users (user_id),
    film_id INTEGER REFERENCES films (film_id)
);
