package com.ewersson.netflix_api_app.service

import com.ewersson.netflix_api_app.model.Category
import com.ewersson.netflix_api_app.model.Movie
import com.ewersson.netflix_api_app.repository.CategoryRepository
import com.ewersson.netflix_api_app.repository.MovieRepository
import com.ewersson.netflix_api_app.service.exception.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService(
    @Autowired
    private val categoryRepository: CategoryRepository,

    @Autowired
    private val movieRepository: MovieRepository
) {

    fun save(category: Category): Category {
        return categoryRepository.save(category)
    }

    fun addMovieToCategory(categoryName: String, movieId: Int) {
        val category: Category = categoryRepository.findByName(categoryName)
            .orElseThrow { ObjectNotFoundException("Category with ID $categoryName not found.") }
        val movie: Movie = movieRepository.findById(movieId)
            .orElseThrow { ObjectNotFoundException("Movie with ID $movieId not found.") }
        movie.category = category
        movieRepository.save(movie)
    }

    fun getCategory(id: Int): Optional<Category> {
        return categoryRepository.findById(id)
    }

    fun getAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    fun deleteCategory(id: Int) {
        if (!categoryRepository.existsById(id)) {
            throw ObjectNotFoundException("Category with ID $id not found!")
        }
        categoryRepository.deleteById(id)
    }

    fun updateCategory(id: Int, updatedCategory: Category): Category {
        val existingCategory = categoryRepository.findById(id).orElseThrow {
            ObjectNotFoundException("Category with ID $id not found!")
        }
        return categoryRepository.save(
            existingCategory.copy(
                name = updatedCategory.name
            )
        )
    }
}