CREATE TABLE MOVIES(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    duration TIME NOT NULL
);

CREATE TABLE SCREENINGROOMS(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    rows INTEGER NOT NULL,
    columns INTEGER NOT NULL
);

CREATE TABLE SCREENINGS(
    id SERIAL PRIMARY KEY,
    movie_id INTEGER NOT NULL,
    screening_room_id INTEGER NOT NULL,
    screening_time TIME NOT NULL
);

INSERT INTO
    MOVIES (id, title, duration)
VALUES
    (1, 'Reservoir Dogs', '1:39:00'),
    (2, 'Django', '2:45:00'),
    (3, 'Once Upon A Time In Hollywood', '2:41:00'),
    (4, 'Hateful Eight', '2:48:00'),
    (5, 'Four Rooms', '1:38'),
    (6, 'From Dusk Till Dawn', '1:48'),
    (7, 'Jackie Brown', '2:38'),
    (8, 'Grind House', '3:11'),
    (9, 'Inglourious Basterds', '2:33');

INSERT INTO
    SCREENINGROOMS (id, name, rows, columns)
VALUES
    (1, 'Room 1', 5, 5),
    (2, 'Room 2', 5, 5),
    (3, 'Room 3', 5, 5);

INSERT INTO
    SCREENINGS (id, movie_id, screening_room_id, screening_time)
VALUES
    (1, 1, 1, '2022-01-04 10:00:00'),
    (2, 2, 1, '2022-01-04 13:00:00'),
    (3, 3, 1, '2022-01-04 15:00:00'),
    (4, 4, 2, '2022-01-04 10:00:00'),
    (5, 5, 2, '2022-01-04 13:00:00'),
    (6, 6, 2, '2022-01-04 15:00:00'),
    (7, 7, 3, '2022-01-04 10:00:00'),
    (8, 8, 3, '2022-01-04 13:00:00'),
    (9, 9, 3, '2022-01-04 15:00:00');