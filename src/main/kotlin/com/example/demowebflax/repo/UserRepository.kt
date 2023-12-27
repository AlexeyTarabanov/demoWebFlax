package com.example.demowebflax.repo

import com.example.demowebflax.querydata.UserData
import com.example.demowebflax.querydata.UserQueryData
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.r2dbc.postgresql.codec.Json
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.OffsetDateTime

/**
 * Репозиторий для работы с данными пользователей в БД.
 *
 * @property databaseClient Клиент для взаимодействия с базой данных.
 * @property objectMapper Объект-маппер для преобразования данных пользователя из JSON.
 */
@Component
class UserRepository(
        private val databaseClient: DatabaseClient,
        private val objectMapper: ObjectMapper
) {

    /**
     * Получает всех пользователей из базы данных.
     *
     * @return Поток Flux, представляющий все данные пользователей.
     */
    fun getAll(): Flux<UserQueryData> =
            databaseClient
                    .sql("""
                SELECT * FROM users
                """.trimIndent()
                    )
                    .fetch() // выполняет запрос к БД и получает результ
                    .all() // возвращает Flux, представляющий все строки результата
                    .map { mapToUserQueryData(it) }



    /**
     * Получает пользователя по идентификатору.
     *
     * @param userId Идентификатор пользователя.
     * @return [Mono] с результатом - пользователем или пустым [Mono], если пользователь не найден.
     */
    fun getUserById(userId: Long): Mono<UserQueryData?> =
            databaseClient
                    .sql(
                            """
                SELECT * FROM users WHERE id = :userId
                """.trimIndent()
                    )
                    .bind("userId", userId) // используется для привязки параметров запроса.
                    .fetch()
                    .one()
                    .mapNotNull {   //  преобразует элементы Flux, фильтруя null значения после преобразования
                        it?.let { mapToUserQueryData(it) } } // вызывает блок кода, только если it не является null


    /**
     * Сохраняет нового пользователя в базе данных.
     *
     * @param newUser Данные нового пользователя.
     * @return [Mono] с результатом - сохраненным пользователем.
     * VALUES (:id, :date, :createdAt, :updatedAt, :createdBy, :updatedBy)
     */
    fun save(newUser: UserQueryData): Mono<UserQueryData> {
        val insertSql = """
        INSERT INTO users (data, created_at, updated_at, created_by, updated_by)
        VALUES (CAST(:data AS JSONB), :createdAt, :updatedAt, CAST(:createdBy AS JSONB), CAST(:updatedBy AS JSONB))
        RETURNING id
    """.trimIndent()


        return databaseClient
                .sql(insertSql)
                .bind("data", objectMapper.writeValueAsString(newUser.date))
                .bind("createdAt", newUser.createdAt)
                .bind("updatedAt", newUser.updateAt)
                .bind("createdBy", newUser.createdBy)
                .bind("updatedBy", newUser.updateBy)
                .fetch()
                .rowsUpdated()
                .then(Mono.just(newUser))
    }



    /**
     * Метод для преобразования строки результата запроса в объект [UserQueryData].
     */
    private fun mapToUserQueryData(result: Map<String, Any>): UserQueryData {
        return UserQueryData(
                id = result["id"] as Long,
                date = objectMapper.readValue<UserData>((result["data"] as Json).asString()),
                createdAt = (result["created_at"] as OffsetDateTime).toLocalDateTime(),
                updateAt = (result["updated_at"] as OffsetDateTime).toLocalDateTime(),
                createdBy = (result["created_by"] as Json).asString(),
                updateBy = (result["updated_by"] as Json).asString()
        )
    }

}
