package com.ewersson.netflix_api_app.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter


@Entity
@Table(name = "movies")
@Getter
@Setter
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

    @Column(name = "genders", nullable = false)
    private var genders: String,
    ) {

}