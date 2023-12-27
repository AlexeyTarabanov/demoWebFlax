--liquibase formatted sql

--changeset tarabanov:release_1_0_2
--comment: Зполнение таблицы Пользователь начальными данными

insert into users (data, created_at, updated_at, created_by, updated_by)
values
    ('{"firstName": "Alex", "lastName": "Mall", "loveJoke": "Did you hear about the new restaurant on the moon? The food is great, but there’s just no atmosphere."}'::jsonb, now(), now(), '{"username": "user_1"}', '{"username": "user_1"}'),
    ('{"firstName": "Alex", "lastName": "Mall", "loveJoke": "What do you call a fake noodle? An impasta."}'::jsonb, now(), now(), '{"username": "user_1"}', '{"username": "user_1"}')

