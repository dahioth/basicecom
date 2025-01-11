package com.othmane.basicecom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping

@SpringBootApplication
@RequestMapping("/api/v1")
class BasicecomApplication

fun main(args: Array<String>) {
    runApplication<BasicecomApplication>(*args)
}
