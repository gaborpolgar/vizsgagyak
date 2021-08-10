create table players
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name          VARCHAR(255),
    date_of_birth DATE,
    position      VARCHAR(255),
    team_id       BIGINT
);