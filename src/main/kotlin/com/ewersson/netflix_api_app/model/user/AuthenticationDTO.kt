package com.ewersson.netflix_api_app.model.user

@JvmRecord
data class AuthenticationDTO(val login: String, val password: String)