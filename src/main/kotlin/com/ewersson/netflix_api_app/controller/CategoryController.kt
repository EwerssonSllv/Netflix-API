package com.ewersson.netflix_api_app.controller

import com.ewersson.netflix_api_app.configs.TokenService
import com.ewersson.netflix_api_app.model.movie.Category
import com.ewersson.netflix_api_app.repository.CategoryRepository
import com.ewersson.netflix_api_app.service.AuthorizationService
import com.ewersson.netflix_api_app.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("categories")
class CategoryController(
    @Autowired
    private val categoryService: CategoryService
) {

    // Create new category
    @PostMapping
    fun create(@RequestBody category: Category): Category {
        return categoryService.save(category)
    }

    // Get category by ID
    @GetMapping("/{id}")
    fun getCategory(@PathVariable id: Int): ResponseEntity<Category> {
        return categoryService.getCategory(id)
            .map { category -> ResponseEntity.ok(category) }
            .orElse(ResponseEntity.notFound().build())
    }

    // Get all categories
    @GetMapping("/all")
    fun getAllCategories(): ResponseEntity<List<Category>> {
        val categories: List<Category> = categoryService.getAllCategories()
        return ResponseEntity.ok(categories)
    }


    // Update category information
    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Int, @RequestBody updatedCategory: Category): ResponseEntity<Category> {
        val category: Category = categoryService.updateCategory(id, updatedCategory)
        return ResponseEntity.ok(category)
    }

    @PutMapping("/{categoryName}/add/{movieId}")
    fun addMovieToCategory(
        @PathVariable categoryName: String,
        @PathVariable movieId: Int
    ): ResponseEntity<String> {
        categoryService.addMovieToCategory(categoryName, movieId)
        return ResponseEntity.ok("Movie with ID $movieId was added to category '$categoryName' successfully.")
    }

    // Delete category
    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Int): ResponseEntity<Void> {
        categoryService.deleteCategory(id)
        return ResponseEntity.noContent().build()
    }

}