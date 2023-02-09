--changeset liquibase:create-table-odds-hub
CREATE TABLE classes
(
    id          SERIAL PRIMARY KEY,
    course_id   SERIAL,
    start_date timestamp,
    end_date timestamp
);

CREATE TABLE courses
(
    id          SERIAL PRIMARY KEY,
    description text,
    image       text,
    instructor  varchar(255),
    name        varchar(255),
    quota       SERIAL
);

CREATE TABLE registration_users
(
    id         UUID PRIMARY KEY,
    class_id   SERIAL,
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    phone varchar(255),
    title varchar(255)
);