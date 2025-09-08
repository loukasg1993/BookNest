package com.morotech.domain.model

data class Book(
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val subjects: List<String>?,
    val coverUrl: String?
)