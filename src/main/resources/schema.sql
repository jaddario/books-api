CREATE TABLE IF NOT EXISTS books (
    id serial PRIMARY KEY,
    title VARCHAR (255),
    author VARCHAR(255),
    subject VARCHAR (255)
);