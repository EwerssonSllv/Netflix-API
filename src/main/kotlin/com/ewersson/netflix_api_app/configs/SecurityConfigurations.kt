package com.ewersson.netflix_api_app.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import jakarta.servlet.Filter

@Configuration
@EnableWebSecurity
class SecurityConfigurations {

    @Autowired
    lateinit var securityFilter: Filter

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/categories").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/categories/all").permitAll()
                    .requestMatchers(HttpMethod.GET, "/movies/{id}").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/movies/all").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/movies/{id}").permitAll()//.hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/movies/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/categories/{id}").permitAll()//.hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/categories/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/categories/{categoryName}/add/{movieId}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/categories/{id}").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
