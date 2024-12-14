package com.ewersson.netflix_api_app.controller

import com.ewersson.netflix_api_app.configs.TokenService
import com.ewersson.netflix_api_app.model.user.AuthenticationDTO
import com.ewersson.netflix_api_app.model.user.LoginResponseDTO
import com.ewersson.netflix_api_app.model.user.RegisterDTO
import com.ewersson.netflix_api_app.model.user.User
import com.ewersson.netflix_api_app.repository.UserRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthenticationController @Autowired constructor(
    private val authenticationManager: AuthenticationManager,
    private val repository: UserRepository,
    private val tokenService: TokenService,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody data: @Valid AuthenticationDTO): ResponseEntity<LoginResponseDTO> {
        val usernamePassword = UsernamePasswordAuthenticationToken(data.login, data.password)
        val auth = authenticationManager.authenticate(usernamePassword)

        val token = tokenService.generateToken(auth.principal as User)

        return ResponseEntity.ok(LoginResponseDTO(token))
    }

    @PostMapping("/register")
    fun register(@RequestBody data: @Valid RegisterDTO): ResponseEntity<Any> {
        if (repository.findByLogin(data.login) != null) return ResponseEntity.badRequest().build<Any>()

        val encryptedPassword = passwordEncoder.encode(data.password)
        val newUser = User(data.login, encryptedPassword, data.role)

        repository.save(newUser)

        return ResponseEntity.ok().build<Any>()
    }
}