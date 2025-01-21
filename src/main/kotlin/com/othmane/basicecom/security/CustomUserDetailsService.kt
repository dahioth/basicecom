package com.othmane.basicecom.security
import com.othmane.basicecom.repositories.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = com.othmane.basicecom.entities.User

@Service
class CustomUserDetailsService (
    private val userRepository : UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByEmail(username).orElseThrow()
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User $username not found")
    }

    private fun ApplicationUser.mapToUserDetails(): UserDetails {
        return User.builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role.name)
            .build()

    }
}
