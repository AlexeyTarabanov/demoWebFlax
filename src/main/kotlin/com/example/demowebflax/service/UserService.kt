package com.example.demowebflax.service

import com.example.demowebflax.dto.UserViewModel
import com.example.demowebflax.querydata.UserQueryData
import com.example.demowebflax.repo.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * Сервис для работы с пользователями.
 *
 * @property userRepository Репозиторий для получения данных о пользователях.
 */
@Service
class UserService(
        private val userRepository: UserRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Получает всех пользователей и преобразует их в список [UserViewModel].
     *
     * @return [Mono] с результатом - списком [UserViewModel].
     */
    fun getAllUsers(): Mono<List<UserViewModel>> =
            userRepository
                    .getAll()
                    .map { userQueryData -> // мапим во view model
                        UserViewModel.of(userQueryData.date)
                    }
                    .collectList() // соберет все элементы во флаксе и положит его в список
                    .doOnNext {
                        logger.debug("getAllUsers: all users {}", it)
                    }

    /**
     * Получает пользователя по идентификатору.
     *
     * @param userId Идентификатор пользователя.
     * @return [Mono] с результатом - пользователем или `null`, если пользователь не найден.
     */
    fun getUserById(userId: Long): Mono<UserQueryData?> =
            userRepository.getUserById(userId)
                    .doOnNext {
                        logger.debug("getUserById: user with id {}", it)
                    }

    /**
     * Создает нового пользователя с получением случайной шутки.
     *
     * @param newUser Данные нового пользователя.
     * @return [Mono] с результатом - созданным пользователем.
     */
    fun createUser(newUser: UserQueryData): Mono<UserQueryData> {
        // Можете добавить дополнительные логики или валидации перед сохранением

        // Сохранение пользователя
        return userRepository.save(newUser)
    }

}