package com.morotech.data.mapper

import com.morotech.data.model.AuthorResponse
import com.morotech.data.model.BookResponse
import com.morotech.domain.model.Author
import com.morotech.domain.model.Book

fun BookResponse.toDomain(): Book {
    val firstAuthor = authors.firstOrNull()?.toDomain()
    val firstSubject = subjects.firstOrNull()
    val imageUrl = formats["image/jpeg"]
        ?: formats.entries.firstOrNull { it.key.startsWith("image/") }?.value

    return Book(
        id = id,
        title = title.trim(),
        authors = listOfNotNull(firstAuthor),
        coverUrl = imageUrl,
        subjects = listOfNotNull(firstSubject)
    )
}

fun AuthorResponse.toDomain(): Author =
    Author(
        name = (name ?: "").trim(),
        birthYear = birthYear,
        deathYear = deathYear
    )