CREATE TABLE IF NOT EXISTS public.books (
    id serial PRIMARY KEY,
    name VARCHAR (255),
    author VARCHAR(255),
    title VARCHAR (255),
    subject VARCHAR (255)
);