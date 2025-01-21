package com.othmane.basicecom.security


import com.othmane.basicecom.repositories.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AppConfiguration {

    @Bean
    fun userDetailsService(userRepository: UserRepository) : UserDetailsService =
        CustomUserDetailsService(userRepository)

    @Bean
    fun authenticationProvider(userRepository: UserRepository) : AuthenticationProvider =
        DaoAuthenticationProvider()
            .also {
                it.setPasswordEncoder(getEncoder())
                it.setUserDetailsService(userDetailsService(userRepository))
            }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration) : AuthenticationManager =
        config.authenticationManager


    @Bean
    fun getEncoder() : PasswordEncoder =
        BCryptPasswordEncoder()
}
