CREATE TABLE MOVIES(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);

CREATE TABLE SCREENINGROOMS(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    rows INTEGER NOT NULL,
    columns INTEGER NOT NULL
);

CREATE TABLE SCREENINGS(
    id SERIAL PRIMARY KEY,
    movie_id INTEGER NOT NULL REFERENCES MOVIES(id),
    screening_room_id INTEGER NOT NULL REFERENCES SCREENINGROOMS(id),
    screening_time TIMESTAMP NOT NULL
);

CREATE TABLE SEATS(
    id SERIAL PRIMARY KEY,
    reservation_id INTEGER NOT NULL,
    row INTEGER NOT NULL,
    column_number INTEGER NOT NULL
);

CREATE TABLE RESERVATIONS(
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    user_surname VARCHAR(100) NOT NULL,
    price NUMERIC(6,2) NOT NULL,
    screening_id INTEGER NOT NULL REFERENCES SCREENINGS(id),
    time TIMESTAMP NOT NULL
);

INSERT INTO
    MOVIES (id, title)
VALUES
    (1, 'Reservoir Dogs'),
    (2, 'Django'),
    (3, 'Once Upon A Time In Hollywood'),
    (4, 'Hateful Eight'),
    (5, 'Four Rooms'),
    (6, 'From Dusk Till Dawn'),
    (7, 'Jackie Brown'),
    (8, 'Grind House'),
    (9, 'Inglourious Basterds');

INSERT INTO
    SCREENINGROOMS (id, name, rows, columns)
VALUES
    (1, 'Room 1', 5, 5),
    (2, 'Room 2', 5, 5),
    (3, 'Room 3', 5, 5);

INSERT INTO
    SCREENINGS (id, movie_id, screening_room_id, screening_time)
VALUES
    (1, 1, 1, '2024-01-04 10:00:00'),
    (2, 2, 1, '2024-01-04 13:00:00'),
    (3, 3, 1, '2024-01-04 15:00:00'),
    (4, 4, 2, '2024-01-04 10:00:00'),
    (5, 5, 2, '2024-01-04 13:00:00'),
    (6, 6, 2, '2024-01-04 15:00:00'),
    (7, 7, 3, '2024-01-04 10:00:00'),
    (8, 8, 3, '2024-01-04 13:00:00'),
    (9, 9, 3, '2024-01-04 15:00:00'),
    (10, 1, 1, '2024-01-05 10:00:00'),
    (11, 2, 1, '2024-01-05 13:00:00'),
    (12, 3, 1, '2024-01-05 15:00:00'),
    (13, 4, 2, '2024-01-05 10:00:00'),
    (14, 5, 2, '2024-01-05 13:00:00'),
    (15, 6, 2, '2024-01-05 15:00:00'),
    (16, 7, 3, '2024-01-05 10:00:00'),
    (17, 8, 3, '2024-01-05 13:00:00'),
    (18, 9, 3, '2024-01-05 15:00:00'),
    (19, 1, 1, '2024-01-06 10:00:00'),
    (20, 2, 1, '2023-01-06 13:00:00'),
    (21, 3, 1, '2023-01-06 15:00:00'),
    (22, 4, 2, '2023-01-06 10:00:00'),
    (23, 5, 2, '2023-01-06 13:00:00'),
    (24, 6, 2, '2023-01-06 15:00:00'),
    (25, 7, 3, '2023-01-06 10:00:00'),
    (26, 8, 3, '2023-01-06 13:00:00'),
    (27, 9, 3, '2023-01-06 15:00:00');

INSERT INTO
    SEATS (id, reservation_id, row, column_number)
VALUES
    (1, 0, 0, 0),
    (2, 0, 0, 1),
    (3, 0, 0, 2),
    (4, 0, 0, 3),
    (5, 1, 1, 0),
    (6, 1, 1, 1),
    (7, 1, 1, 2),
    (8, 1, 1, 3),
    (9, 2, 2, 0),
    (10, 2, 2, 1),
    (11, 2, 2, 2),
    (12, 2, 2, 3),
    (13, 3, 3, 0),
    (14, 3, 3, 1),
    (15, 3, 3, 2),
    (16, 3, 3, 3),
    (17, 4, 4, 0),
    (18, 4, 4, 1),
    (19, 4, 4, 2),
    (20, 4, 4, 3);

INSERT INTO
    RESERVATIONS (id, user_name, user_surname, price, screening_id, time)
VALUES
    (1, 'John', 'Doe', 25.0, 1, '2022-01-04 10:00:00'),
    (2, 'John', 'Doe', 18.0, 2, '2022-01-04 13:00:00'),
    (3, 'John', 'Doe', 25.0, 3, '2022-01-04 15:00:00'),
    (4, 'John', 'Doe', 25.0, 4, '2022-01-04 10:00:00'),
    (5, 'John', 'Doe', 12.5, 5, '2022-01-04 13:00:00'),
    (6, 'John', 'Doe', 12.5, 6, '2022-01-04 15:00:00'),
    (7, 'John', 'Doe', 18.0, 7, '2022-01-04 10:00:00'),
    (8, 'John', 'Doe', 25.0, 8, '2022-01-04 13:00:00'),
    (9, 'John', 'Doe', 25.0, 9, '2022-01-04 15:00:00');