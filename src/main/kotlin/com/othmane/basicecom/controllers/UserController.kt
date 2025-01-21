package com.othmane.basicecom.controllers

import com.othmane.basicecom.dtos.UpdateUserRoleRequestDTO
import com.othmane.basicecom.entities.User
import com.othmane.basicecom.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = ["*"]) // TODO: Add Cors Filter
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun getUsers() : ResponseEntity<MutableList<User>> {
        return ResponseEntity.ok(userService.getUsersService())
    }

    @PatchMapping("/{userId}/role")
    fun updateUserRole(
        @PathVariable userId: Long,
        @RequestBody updateUserRoleRequest: UpdateUserRoleRequestDTO
    ): ResponseEntity<User> {
        return ResponseEntity.ok(userService.updateUserRoleService(userId, updateUserRoleRequest.role))
    }
}