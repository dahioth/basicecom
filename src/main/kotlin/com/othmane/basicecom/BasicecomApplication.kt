package com.othmane.basicecom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.filter.CommonsRequestLoggingFilter

@SpringBootApplication
class BasicecomApplication {

    @Bean
    fun logger() : CommonsRequestLoggingFilter {
        val filter = CommonsRequestLoggingFilter()
        filter.setIncludeQueryString(true)
        filter.setIncludeHeaders(true)
        filter.setIncludePayload(true)


        return filter
    }
}

fun main(args: Array<String>) {
    runApplication<BasicecomApplication>(*args)
}
