--liquibase formatted sql

--changeset tarabanov:release_1_0_0
--comment: Создание таблицы Пользователь

create table users
(
    id         bigserial primary key,
    data       jsonb                    not null default '{}'::jsonb,
    created_at jsonb                    not null default '{}'::jsonb,
    updated_at timestamp with time zone not null,
    created_by jsonb                    not null default '{}'::jsonb,
    updated_by jsonb                    not null default '{}'::jsonb
)
--rollback drop table users;
