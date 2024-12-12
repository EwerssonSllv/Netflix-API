package com.ewersson.netflix_api_app.repository

import com.ewersson.netflix_api_app.model.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Movie, Int>