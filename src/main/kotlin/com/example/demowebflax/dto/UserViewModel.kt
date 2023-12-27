package com.example.demowebflax.dto

import com.example.demowebflax.querydata.UserData

/**
 * Класс, представляющий модель представления пользователя для использования в веб-приложении.
 *
 * @property firstName Имя пользователя.
 * @property lastName Фамилия пользователя.
 * @property loveJoke Любимый анекдот пользователя.
 */
class UserViewModel(
        val firstName: String, val lastName: String, val loveJoke: String
) {
    companion object {

        /**
         * Создает экземпляр UserViewModel на основе данных о пользователе.
         *
         * @param source Данные пользователя, используемые для создания модели представления.
         * @return Экземпляр UserViewModel, созданный на основе данных о пользователе.
         */
        fun of(source: UserData): UserViewModel = UserViewModel(
                firstName = source.firstName, lastName = source.lastName, loveJoke = source.loveJoke
        )
    }
}