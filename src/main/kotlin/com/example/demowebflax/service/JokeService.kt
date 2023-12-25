package com.example.demowebflax.service

import com.example.demowebflax.proxy.JokeProxyService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * Сервис для работы с шутками.
 * Этот класс отвечает за предоставление случайных шуток, используя Joke Proxy Service.
 *
 * @param jokeProxyService Сервис для взаимодействия с Joke Proxy Service.
 */

@Service
class JokeService(
        private val jokeProxyService: JokeProxyService
) {

    /**
     * Получает случайную шутку в виде строки.
     *
     * @return Mono, возвращающий строку, представляющую случайную шутку.
     */
    fun getRandomJoke(): Mono<String> =
            jokeProxyService
                    .getRandomJoke()
                    .map {
                        "${it.setup} ${it.punchline}"
                    }
}