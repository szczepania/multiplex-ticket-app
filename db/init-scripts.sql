CREATE TABLE MOVIES(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    duration TIME NOT NULL
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
    (9, 'Inglourious Basterds', '2:33')