DROP TABLE IF EXISTS books;

CREATE TABLE books (
    id serial PRIMARY KEY,
    title VARCHAR (255),
    author VARCHAR(255),
    subject VARCHAR (255)
);