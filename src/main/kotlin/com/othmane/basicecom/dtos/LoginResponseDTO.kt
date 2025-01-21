package com.othmane.basicecom.dtos

import com.othmane.basicecom.enums.Role

data class LoginResponseDTO(
    val token: String,
    val user: UserDTO
)
