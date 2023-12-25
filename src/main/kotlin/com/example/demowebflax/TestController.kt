package com.example.demowebflax


import com.example.demowebflax.service.JokeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


/**
 * Контроллер для тестирования.
 * Этот контроллер предоставляет эндпоинт для получения случайной шутки.
 *
 * @param jokeService Сервис для работы с шутками.
 */

@RestController
class TestController(
        private val jokeService: JokeService
) {


    /**
     * Контроллер для тестирования.
     * Этот контроллер предоставляет эндпоинт для получения случайной шутки.
     *
     * @param jokeService Сервис для работы с шутками.
     */
    @GetMapping("/")
    fun test(): Mono<String> = jokeService.getRandomJoke()
}