package com.ewersson.netflix_api_app.model.movie

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "similar_movies")
data class SimilarMovie(

    @OneToMany(mappedBy = "similarMovie", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var movies: MutableList<Movie>? = null
){}