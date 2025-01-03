package com.ewersson.netflix_api_app.repository

import com.ewersson.netflix_api_app.model.movie.SimilarMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SimilarMovieRepository : JpaRepository<SimilarMovie, Int>
