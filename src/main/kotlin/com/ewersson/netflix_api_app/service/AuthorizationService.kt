package com.ewersson.netflix_api_app.service

import com.ewersson.netflix_api_app.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private val repository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return repository.findByLogin(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")
    }
}
