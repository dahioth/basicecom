package com.othmane.basicecom.dtos

import com.othmane.basicecom.enums.Role

data class UserDTO(
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: Role
)
