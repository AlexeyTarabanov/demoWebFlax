package com.example.demowebflax.querydata

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

/**
 * Класс, представляющий пользователя в базе данных.
 *
 * @property id Уникальный идентификатор пользователя.
 * @property date Данные пользователя.
 * @property createdAt Дата и время создания пользователя.
 * @property updateAt Дата и время последнего обновления пользователя.
 * @property createdBy Имя пользователя, создавшего запись.
 * @property updateBy Имя пользователя, обновившего запись.
 */
data class UserQueryData(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val date: UserData,
        val createdAt: LocalDateTime,
        val updateAt: LocalDateTime,
        val createdBy: String,
        val updateBy: String,
)
