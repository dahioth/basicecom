package com.othmane.basicecom.services

import com.othmane.basicecom.entities.User
import com.othmane.basicecom.enums.Role
import com.othmane.basicecom.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun getUsersService(): MutableList<User> = userRepository.findAll()

    fun updateUserRoleService(userId: Long, role: Role): User {
        val user = userRepository.findById(userId)
        .orElseThrow { RuntimeException("User not found") }
        user.role = role
        return userRepository.save(user)
    }
}