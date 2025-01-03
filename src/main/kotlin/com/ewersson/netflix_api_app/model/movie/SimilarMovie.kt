package com.ewersson.netflix_api_app.model.movie

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "similar_movies")
data class SimilarMovie(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    var id: Int = 0,

    @OneToMany(mappedBy = "similarMovie", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val movies: MutableList<Movie> = mutableListOf()
){ fun getInt(): Int = id}