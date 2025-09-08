package com.morotech.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.morotech.domain.model.Author
import com.morotech.domain.model.Book

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val authorName: String?,
    val authorBirthYear: Int?,
    val authorDeathYear: Int?,
    val coverUrl: String?,
    val subject: String?
)

fun BookEntity.toDomain(): Book =
    Book(
        id = id,
        title = title,
        authors = listOfNotNull(
            authorName?.let {
                Author(
                    name = it,
                    birthYear = authorBirthYear,
                    deathYear = authorDeathYear
                )
            }
        ),
        coverUrl = coverUrl,
        subjects = listOfNotNull(subject)
    )

fun Book.toEntity(): BookEntity {
    val firstAuthor = authors.firstOrNull()
    return BookEntity(
        id = id,
        title = title,
        authorName = firstAuthor?.name,
        authorBirthYear = firstAuthor?.birthYear,
        authorDeathYear = firstAuthor?.deathYear,
        coverUrl = coverUrl,
        subject = subjects?.firstOrNull()
    )
}