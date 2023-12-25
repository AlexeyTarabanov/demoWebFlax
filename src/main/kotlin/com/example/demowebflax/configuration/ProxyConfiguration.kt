package com.example.demowebflax.configuration

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

/**
 * Класс конфигурации для настройки веб-клиента для взаимодействия со службой Joke.
 * (отвечает за вызов стороннего веб-сервиса)
 * Этот класс помечен {@code @Configuration} и {@code @EnableConfigurationProperties}.
 * чтобы указать, что он предоставляет конфигурацию и позволяет использовать свойства конфигурации.
 *
 * @param properties Свойства конфигурации для веб-клиента Joke.
 */
@Configuration
@EnableConfigurationProperties(
        value = [JokeWebClientProperties::class]
)
class ProxyConfiguration(
        private val properties: JokeWebClientProperties,
) {

    /**
     * Создает и настраивает компонент WebClient для взаимодействия со службой Joke.
     *
     * @return Настроенный экземпляр WebClient.
     */
    @Bean
    fun jokeWebClient(): WebClient {
        return WebClient.builder()
                .baseUrl(properties.baseUrl)
                .build()
    }
}