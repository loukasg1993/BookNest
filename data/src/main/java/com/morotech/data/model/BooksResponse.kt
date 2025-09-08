package com.morotech.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksResponse(
    @param:Json(name = "count") val count: Int,
    @param:Json(name = "next") val next: String?,
    @param:Json(name = "previous") val previous: String?,
    @param:Json(name = "results") val results: List<BookResponse>
)

@JsonClass(generateAdapter = true)
data class BookResponse(
    @param:Json(name = "id") val id: Int,
    @param:Json(name = "title") val title: String,
    @param:Json(name = "authors") val authors: List<AuthorResponse>,
    @param:Json(name = "subjects") val subjects: List<String>,
    @param:Json(name = "formats") val formats: Map<String, String>
)

@JsonClass(generateAdapter = true)
data class AuthorResponse(
    @param:Json(name = "name") val name: String? = null,
    @param:Json(name = "birth_year") val birthYear: Int?,
    @param:Json(name = "death_year") val deathYear: Int?
)
