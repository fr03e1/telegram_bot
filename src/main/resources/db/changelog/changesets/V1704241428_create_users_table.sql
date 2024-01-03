-- liquibase formatted sql

-- changeset a.bocharov:V1704241428_create_users_table
CREATE TABLE users
(
    id            SERIAL       NOT NULL PRIMARY KEY,

    username     VARCHAR(256) NOT NULL,
    tg_id        BIGINT  NOT NULL UNIQUE
);
