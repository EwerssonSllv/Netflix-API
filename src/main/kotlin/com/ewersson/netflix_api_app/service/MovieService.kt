package com.ewersson.netflix_api_app.service

import com.ewersson.netflix_api_app.model.Movie
import com.ewersson.netflix_api_app.repository.MovieRepository
import com.ewersson.netflix_api_app.service.exception.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class MovieService(
    @Autowired
    private val movieRepository: MovieRepository
) {

    fun save(movie: Movie): Movie {
        return movieRepository.save(movie)
    }

    fun getMovie(id: Int): Optional<Movie> {
        return movieRepository.findById(id)
    }

    fun getAllMovies(): List<Movie> {
        return movieRepository.findAll()
    }

    fun deleteMovie(id: Int) {
        movieRepository.deleteById(id)
    }

    fun updateMovie(id: Int, updateMovie: Movie): Movie {
        val movie = movieRepository.findById(id).orElseThrow {
            ObjectNotFoundException("Movie Not Found!")
        }
        movie.setTitle(updateMovie.getTitle())
        movie.setImage(updateMovie.getImage())
        movie.setCover(updateMovie.getCover())
        movie.setDescription(updateMovie.getDescription())
        movie.setCast(updateMovie.getCast())
        return movieRepository.save(movie)
    }

}