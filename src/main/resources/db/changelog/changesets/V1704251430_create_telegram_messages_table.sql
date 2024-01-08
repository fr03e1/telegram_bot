-- liquibase formatted sql

-- changeset a.bocharov:V1704251430_create_telegram_messages_table
CREATE TABLE telegram_messages
(
    id            SERIAL       NOT NULL PRIMARY KEY,

    key           VARCHAR(255) NOT NULL UNIQUE,
    message       TEXT         NOT NULL
);

INSERT INTO telegram_messages(key, message)
VALUES
    ('default', 'Я вас не понимаю, воспользуйтесь командой /start.'),
    ('start', 'Для продолжения выберите необходимое действие из предложенных ниже вариантов.'),
    ('category', 'Выберите категорию:'),
    ('expense', 'Введите сумму:')
;

