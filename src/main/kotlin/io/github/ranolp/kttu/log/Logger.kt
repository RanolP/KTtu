package io.github.ranolp.kttu.log

import io.github.ranolp.kttu.settings.LogSetting
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Logger {
    enum class LogLevel(val level: Int) {
        ALL(Integer.MIN_VALUE),
        INFO(0),
        WARNING(1),
        ERROR(2),
        OFF(Integer.MAX_VALUE)
    }

    private var formatter: DateTimeFormatter? = null
    var level: LogLevel = LogLevel.INFO

    private fun log(level: LogLevel, text: String) {
        if (Logger.level.level > level.level) {
            return
        }
        val now = LocalDateTime.now()
        if (formatter === null) {
            formatter = DateTimeFormatter.ofPattern(LogSetting.INSTANCE.dateSyntax)
        }
        println("[${now.format(formatter)}/$level] $text")
    }

    fun info(text: String) {
        log(LogLevel.INFO, text)
    }

    fun warning(text: String) {
        log(LogLevel.WARNING, text)
    }

    fun error(text: String) {
        log(LogLevel.ERROR, text)
    }
}