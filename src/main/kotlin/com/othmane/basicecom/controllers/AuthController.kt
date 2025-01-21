package com.othmane.basicecom.controllers

import com.othmane.basicecom.dtos.LoginRequestDTO
import com.othmane.basicecom.dtos.LoginResponseDTO
import com.othmane.basicecom.dtos.RegisterRequestDTO
import com.othmane.basicecom.services.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = ["*"])
class AuthController {

    @Autowired
    lateinit var authService: AuthService

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        return ResponseEntity(authService.loginService(loginRequest), HttpStatus.CREATED)
    }

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequestDTO): ResponseEntity<Unit> {
        return ResponseEntity(authService.registerService(registerRequest), HttpStatus.CREATED)
    }
}