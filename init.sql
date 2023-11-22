CREATE TABLE _user (
   id SERIAL PRIMARY KEY,
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE SEQUENCE _user_seq START 1;
