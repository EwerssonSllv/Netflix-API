package com.ewersson.netflix_api_app.controller

import com.ewersson.netflix_api_app.model.movie.Movie
import com.ewersson.netflix_api_app.model.movie.SimilarMovie
import com.ewersson.netflix_api_app.service.SimilarMovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("similar")
class SimilarMovieController(private val similarMovieService: SimilarMovieService) {

    @PostMapping("/{similarMovieId}/add/{movieId}")
    fun addMovieToSimilarGroup(
        @PathVariable similarMovieId: Int,
        @PathVariable movieId: Int
    ): ResponseEntity<SimilarMovie> {
        val updatedGroup = similarMovieService.addMovieToSimilarGroup(similarMovieId, movieId)
        return ResponseEntity.ok(updatedGroup)
    }

    @DeleteMapping("/{similarMovieId}/remove/{movieId}")
    fun removeMovieFromSimilarGroup(
        @PathVariable similarMovieId: Int,
        @PathVariable movieId: Int
    ): ResponseEntity<SimilarMovie> {
        val updatedGroup = similarMovieService.removeMovieFromSimilarGroup(similarMovieId, movieId)
        return ResponseEntity.ok(updatedGroup)
    }

}
