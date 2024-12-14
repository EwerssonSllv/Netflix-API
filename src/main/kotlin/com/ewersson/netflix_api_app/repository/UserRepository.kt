package com.ewersson.netflix_api_app.repository

import com.ewersson.netflix_api_app.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String> {
    fun findByLogin(login: String?): UserDetails?
}