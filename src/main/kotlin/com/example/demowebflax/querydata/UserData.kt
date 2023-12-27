package com.example.demowebflax.querydata

/**
 * Класс, представляющий данные пользователя для возврата на фронт.
 *
 * @property firstName Имя пользователя.
 * @property lastName Фамилия пользователя.
 * @property loveJoke Любимый анекдот пользователя.
 */
data class UserData(
        val firstName: String,
        val lastName: String,
        val loveJoke: String
)
