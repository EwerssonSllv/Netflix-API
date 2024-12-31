package com.ewersson.netflix_api_app.model.movie

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private var id: Int,

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank
    private var title: String,

    @Column(name = "image", nullable = false)
    @NotBlank
    private var image: String,

    @Column(name = "cover", nullable = false)
    @NotBlank
    private var cover: String,

    @Lob
    @NotBlank
    @Column(name = "description", nullable = false)
    private var description: String,

    @Column(name = "cast", nullable = false)
    private var cast: String,

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    var category: Category? = null
) {

    fun getId(): Int = id
    fun getTitle(): String = title
    fun getImage(): String = image
    fun getCover(): String = cover
    fun getDescription(): String = description
    fun getCast(): String = cast


    fun setTitle(newTitle: String) {
        title = newTitle
    }

    fun setImage(newImage: String) {
        image = newImage
    }

    fun setCover(newCover: String) {
        cover = newCover
    }

    fun setDescription(newDescription: String) {
        description = newDescription
    }

    fun setCast(newCast: String) {
        cast = newCast
    }

}