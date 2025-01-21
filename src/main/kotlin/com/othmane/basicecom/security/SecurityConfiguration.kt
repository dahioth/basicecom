package com.othmane.basicecom.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfiguration (
    val authenticationProvider: AuthenticationProvider,
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity, jwtFilter: JwtFilter): DefaultSecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/api/v1/auth/login").permitAll()
                    .requestMatchers("/api/v1/auth/register").permitAll()
                    .requestMatchers(HttpMethod.OPTIONS).permitAll()
                    .requestMatchers(HttpMethod.GET,"/api/v1/orders").authenticated()
                    .requestMatchers(HttpMethod.POST,"/api/v1/orders").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/v1/products").authenticated()
                    .anyRequest().hasRole("ADMIN")
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}