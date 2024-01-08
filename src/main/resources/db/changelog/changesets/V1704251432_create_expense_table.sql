-- liquibase formatted sql

-- changeset a.bocharov:V1704251432_create_expenses_table
CREATE TABLE expenses
(
    id            SERIAL       NOT NULL PRIMARY KEY,

    value        NUMERIC(19, 2) NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT NOW(),

    category_id  INT,

    CONSTRAINT category_id FOREIGN KEY (category_id)
        REFERENCES categories (id)
        ON UPDATE NO ACTION ON DELETE NO ACTION
);