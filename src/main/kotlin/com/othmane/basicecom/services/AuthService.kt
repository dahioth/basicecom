package com.othmane.basicecom.services

import com.othmane.basicecom.dtos.LoginRequestDTO
import com.othmane.basicecom.dtos.LoginResponseDTO
import com.othmane.basicecom.dtos.RegisterRequestDTO
import com.othmane.basicecom.dtos.UserDTO
import com.othmane.basicecom.entities.User
import com.othmane.basicecom.enums.Role
import com.othmane.basicecom.repositories.UserRepository
import com.othmane.basicecom.security.CustomUserDetailsService
import com.othmane.basicecom.security.TokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) {

    fun loginService(loginRequest: LoginRequestDTO): LoginResponseDTO {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.email,
                loginRequest.password
            )
        )

        val accessToken = tokenService.generateToken(userDetailsService.loadUserByUsername(loginRequest.email))
        val user = userRepository.findByEmail(loginRequest.email).get()

        return LoginResponseDTO(accessToken, UserDTO(user.email, user.firstName, user.lastName, user.role))
    }

    fun registerService(registerRequest: RegisterRequestDTO) {
        if (userRepository.existsByEmail(registerRequest.email)) {
            throw IllegalArgumentException("User with email ${registerRequest.email} already exists.")
        }

        val newUser = User(
            registerRequest.firstName,
            registerRequest.lastName,
            registerRequest.email,
            encoder.encode(registerRequest.password)
        )

        userRepository.save(newUser)

        return

    }
}