package com.othmane.basicecom.repositories

import com.othmane.basicecom.entities.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : BaseRepository<User> {
    fun findByEmail(userEmail: String) : Optional<User>

    fun existsByEmail(email: String): Boolean
}