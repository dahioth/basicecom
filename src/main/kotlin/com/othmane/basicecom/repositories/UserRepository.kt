package com.othmane.basicecom.repositories

import com.othmane.basicecom.entities.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : BaseRepository<User> {
    fun findByEmail(userEmail: String) : User
}