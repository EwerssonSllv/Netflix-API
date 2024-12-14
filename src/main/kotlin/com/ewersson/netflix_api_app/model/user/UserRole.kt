package com.ewersson.netflix_api_app.model.user

import lombok.Getter

@Getter
enum class UserRole(
    private val role: String) {
    ADMIN("admin"),
    USER("user")
}