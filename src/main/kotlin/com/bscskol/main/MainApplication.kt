package com.bscskol.main

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["com.bscskol.*"])
class MainApplication {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder? =
            BCryptPasswordEncoder()

}

fun main(args: Array<String>) {
    runApplication<MainApplication>(*args)
}
