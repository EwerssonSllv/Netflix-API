package com.ewersson.netflix_api_app.model.movie

import com.fasterxml.jackson.annotation.JsonManagedReference

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name="categories")
data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    val name: String,

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val movies: MutableList<Movie> = mutableListOf()
)