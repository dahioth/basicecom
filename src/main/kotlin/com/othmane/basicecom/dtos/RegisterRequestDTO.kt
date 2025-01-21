package com.othmane.basicecom.dtos

data class RegisterRequestDTO(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)
