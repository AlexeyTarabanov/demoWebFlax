--liquibase formatted sql

--changeset tarabanov:release_1_0_1
--comment: Изменение типа поля created_at

-- Создание временного столбца
ALTER TABLE users ADD COLUMN created_at_temp TIMESTAMP WITH TIME ZONE;

-- Обновление данных в новом столбце
UPDATE users SET created_at_temp = created_at::timestamp with time zone;

-- Удаление старого столбца
ALTER TABLE users DROP COLUMN created_at;

-- Переименование временного столбца
ALTER TABLE users RENAME COLUMN created_at_temp TO created_at;

-- Добавление NOT NULL
ALTER TABLE users ALTER COLUMN created_at SET NOT NULL;
