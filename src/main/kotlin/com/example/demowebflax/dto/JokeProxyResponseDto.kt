package com.example.demowebflax.dto


/**
 * Класс данных, представляющий ответ DTO на шутку, полученную из прокси-службы шутки.
 * Этот класс предназначен для хранения завязок и кульминации шутки.
 *
 * @param setup Подготовительная часть шутки.
 * @param punchline Кульминационная часть шутки.
 */
data class JokeProxyResponseDto(
        val setup: String,
        val punchline: String
)