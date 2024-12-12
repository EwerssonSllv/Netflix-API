package com.ewersson.netflix_api_app.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    val name: String,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_list_id")
    val movies: List<Movie> = mutableListOf()
) {
}