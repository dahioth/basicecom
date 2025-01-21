package com.othmane.basicecom.dtos

import com.othmane.basicecom.enums.Role

data class UpdateUserRoleRequestDTO(
    val role: Role
)