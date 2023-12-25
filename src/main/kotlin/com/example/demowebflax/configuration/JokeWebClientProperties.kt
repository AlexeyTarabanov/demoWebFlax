package com.example.demowebflax.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Свойства конфигурации для веб-клиента Joke.
 * Этот класс помечен {@code @ConfigurationProperties}, что позволяет
 * автоматически заполняется значениями из конфигурации приложения.
 *
 * @param baseUrl Базовый URL-адрес службы шуток.
 */

@ConfigurationProperties(prefix = "application.joke-service")
data class JokeWebClientProperties(
        val baseUrl: String
)
