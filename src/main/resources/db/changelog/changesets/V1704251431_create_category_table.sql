-- liquibase formatted sql

-- changeset a.bocharov:V1704251431_create_categories_table
CREATE TABLE categories
(
    id            SERIAL       NOT NULL PRIMARY KEY,

    value        VARCHAR(255) NOT NULL UNIQUE,
    comment      TEXT         NOT NULL DEFAULT '',
    created_at   TIMESTAMP    NOT NULL DEFAULT NOW()
);

INSERT INTO categories(value)
VALUES
    ('Продукты'),
    ('Транспорт'),
    ('ЖКХ'),
    ('Одежда'),
    ('Еда вне дома'),
    ('Прочее')
;

