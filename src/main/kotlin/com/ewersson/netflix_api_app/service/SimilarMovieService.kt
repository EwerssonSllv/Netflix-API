package com.ewersson.netflix_api_app.service

import com.ewersson.netflix_api_app.model.movie.Movie
import com.ewersson.netflix_api_app.model.movie.SimilarMovie
import com.ewersson.netflix_api_app.repository.MovieRepository
import com.ewersson.netflix_api_app.repository.SimilarMovieRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class SimilarMovieService(
    private val similarMovieRepository: SimilarMovieRepository,
    private val movieRepository: MovieRepository
) {

    fun addSimilarMovies(similarMovieId: Int, movieId: Int): SimilarMovie {
        val similarMovie = similarMovieRepository.findById(similarMovieId)
            .orElseThrow { EntityNotFoundException("SimilarMovie not found") }

        val movie = movieRepository.findById(movieId)
            .orElseThrow { EntityNotFoundException("Movie not found") }

        // Associando o filme ao grupo de filmes similares
        movie.similarMovie = similarMovie
        similarMovie.movies.add(movie)

        // Salvando as alterações
        movieRepository.save(movie)  // Atualiza o filme
        similarMovieRepository.save(similarMovie)  // Atualiza o grupo de filmes similares

        return similarMovie
    }

    fun removeMovieFromSimilarGroup(similarMovieId: Int, movieId: Int): SimilarMovie {
        val similarMovie = similarMovieRepository.findById(similarMovieId)
            .orElseThrow { RuntimeException("SimilarMovie not found") }
        val movie = movieRepository.findById(movieId)
            .orElseThrow { RuntimeException("Movie not found") }
        similarMovie.movies?.remove(movie)
        movie.similarMovie = null
        movieRepository.save(movie)
        return similarMovieRepository.save(similarMovie)
    }
}