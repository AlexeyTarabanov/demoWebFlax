package com.example.demowebflax.proxy

import com.example.demowebflax.dto.JokeProxyResponseDto
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


/**
 * Класс Service для взаимодействия с прокси-сервисом Joke.
 * Этот класс отвечает за получение случайных шуток из службы Joke Proxy.
 *
 * @property jokeWebClient Веб-клиент, используемый для отправки запросов к прокси-сервису Joke
 */
@Service
class JokeProxyService(
        private val jokeWebClient: WebClient
) {

    // like static class
    companion object {
        private const val JOKE_PATH = "/random_joke"
    }

    /**
     * Получает случайную шутку из прокси-сервиса Joke
     *
     * @return Mono возвращающий JokeProxyResponseDto представляющая случайную шутку.
     */
    fun getRandomJoke(): Mono<JokeProxyResponseDto> =
            jokeWebClient
                    .get()
                    .uri(JOKE_PATH)
                    .retrieve()
                    .bodyToMono(object : ParameterizedTypeReference<JokeProxyResponseDto>() {})
}