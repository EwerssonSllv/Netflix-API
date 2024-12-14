package com.ewersson.netflix_api_app.repository

import com.ewersson.netflix_api_app.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, Int> {
    fun findByName(name: String): Optional<Category>
}

