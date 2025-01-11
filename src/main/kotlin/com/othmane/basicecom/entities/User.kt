package com.othmane.basicecom.entities

import com.othmane.basicecom.enums.Role
import jakarta.persistence.*

@Entity
@Table(name = "Users")
data class User(
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    @Enumerated(EnumType.STRING) var role: Role,
    @OneToMany
    @JoinTable(name = "user_id")
    var orders: MutableList<Order> = mutableListOf(),
    ) : BaseEntity()