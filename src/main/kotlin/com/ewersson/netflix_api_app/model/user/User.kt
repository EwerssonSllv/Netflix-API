package com.ewersson.netflix_api_app.model.user

import jakarta.persistence.*
import lombok.Getter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
@Getter
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    val login: String,
    private val password: String,
    @Enumerated(EnumType.STRING)
    val role: UserRole
) : UserDetails {

    fun getUserLogin(): String {
        return login
    }

    constructor(login: String, password: String, role: UserRole) : this(null, login, password, role)

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return if (role == UserRole.ADMIN) {
            listOf(
                SimpleGrantedAuthority("ROLE_ADMIN"),
                SimpleGrantedAuthority("ROLE_USER")
            )
        } else {
            listOf(SimpleGrantedAuthority("ROLE_USER"))
        }
    }

    override fun getPassword(): String = password

    override fun getUsername(): String = login

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}


