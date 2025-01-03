package com.ewersson.netflix_api_app.controller

import com.ewersson.netflix_api_app.model.movie.Movie
import com.ewersson.netflix_api_app.service.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("movies")
class MovieController(
    @Autowired
    private val movieService: MovieService
) {

    // Create new movie
    @PostMapping
    fun create(@RequestBody movie: Movie): Movie {
        return movieService.save(movie)
    }

    // Get Book
    @GetMapping("/{id}")
    fun getMovie(@PathVariable id: Int): ResponseEntity<Movie> {
        return movieService.getMovie(id)
            .map { movie -> ResponseEntity.ok(movie) }
            .orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/all")
    fun getAllMovies(): ResponseEntity<List<Movie>> {
        val movie: List<Movie> = movieService.getAllMovies()
        return ResponseEntity.ok(movie)
    }

    // Update information about the movie
    @PutMapping("/{id}")
    fun updateMovie(@PathVariable id: Int, @RequestBody updatedBook: Movie): ResponseEntity<Movie> {
        val movie: Movie = movieService.updateMovie(id, updatedBook)
        return ResponseEntity.ok(movie)
    }

    // Delete movie
    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: Int): ResponseEntity<Void> {
        movieService.deleteMovie(id)
        return ResponseEntity.noContent().build()
    }

    // Add similar movie
    @PostMapping("/{id}/similar")
    fun addSimilarMovie(@PathVariable id: Int, @RequestBody similarMovieId: Int): ResponseEntity<Movie> {
        val updatedMovie = movieService.addSimilarMovie(id, similarMovieId)
        return ResponseEntity.ok(updatedMovie)
    }

    // Get similar movies
    @GetMapping("/{id}/similar")
    fun getSimilarMovies(@PathVariable id: Int): ResponseEntity<List<Movie>> {
        val similarMovies = movieService.getSimilarMovies(id)
        return ResponseEntity.ok(similarMovies)
    }

    // Remove similar movie
    @DeleteMapping("/{id}/similar/{similarMovieId}")
    fun removeSimilarMovie(@PathVariable id: Int, @PathVariable similarMovieId: Int): ResponseEntity<Movie> {
        val updatedMovie = movieService.removeSimilarMovie(id, similarMovieId)
        return ResponseEntity.ok(updatedMovie)
    }
}