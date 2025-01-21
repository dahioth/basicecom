package com.othmane.basicecom.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.othmane.basicecom.enums.Role
import jakarta.persistence.*

@Entity
@Table(name = "Users")
data class User(
    var firstName: String,
    var lastName: String,
    var email: String,
    @JsonIgnore()
    var password: String,
    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER
) : BaseEntity()