package com.ewersson.netflix_api_app.model.user

@JvmRecord
data class RegisterDTO(
    val login: String,
    val password: String,
    val role: UserRole)