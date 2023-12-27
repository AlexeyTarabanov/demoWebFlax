package com.example.demowebflax.controller

import com.example.demowebflax.dto.UserViewModel
import com.example.demowebflax.proxy.JokeProxyService
import com.example.demowebflax.querydata.UserData
import com.example.demowebflax.querydata.UserQueryData
import com.example.demowebflax.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.time.LocalDateTime


/**
 * Контроллер для обработки запросов, связанных с пользователями.
 *
 * @property userService Сервис для работы с пользователями.
 */
@RestController
@RequestMapping("/user")
class UserController(
        private val userService: UserService,
        private val jokeProxyService: JokeProxyService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Обработчик GET-запроса для получения всех пользователей.
     *
     * @return [Mono] с результатом - списком [UserViewModel].
     */
    @GetMapping("/all")
    fun getAll(): Mono<List<UserViewModel>> =
            userService.getAllUsers()

    /**
     * Получает пользователя по идентификатору.
     *
     * @param userId Идентификатор пользователя.
     * @return [Mono] с результатом - пользователем или `null`, если пользователь не найден.
     */
    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Long): Mono<UserQueryData?> =
            userService.getUserById(userId)


    /**
     * Обработчик POST-запроса для создания нового пользователя с получением случайной шутки.
     *
     * @param userData Данные нового пользователя.
     * @return [Mono] с результатом - созданным пользователем.
     */
    @PostMapping("/create")
    fun createUserWithJoke(@RequestBody userData: UserData): Mono<UserQueryData> {
        // Получение случайной шутки
        val jokeMono = jokeProxyService.getRandomJoke()

        // Объединение данных пользователя и шутки
        return jokeMono.flatMap { joke ->

            // Логируем данные перед созданием нового пользователя
            logger.debug("Received userData: {}", userData)
            logger.debug("Received joke: {}", joke)

            // Создание нового пользователя
            val newUserDate = UserData(
                    firstName = userData.firstName,
                    lastName = userData.lastName,
                    loveJoke = "${joke.setup} ${joke.punchline}"
            )

            val newUser = UserQueryData(
                    date = newUserDate,
                    createdAt = LocalDateTime.now(),
                    updateAt = LocalDateTime.now(),
                    createdBy = """{"username": "user_1"}""",
                    updateBy = """{"username": "user_1"}"""
            )

            // Сохранение пользователя и возврат результата
            userService.createUser(newUser)
        }
    }
}