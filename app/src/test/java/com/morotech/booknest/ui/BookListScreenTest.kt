package com.morotech.booknest.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import com.morotech.domain.model.Author
import com.morotech.domain.model.Book
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)
//class BookScreensTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun bookList_displaysBooks_andTriggersClick() {
//        val book = Book(
//            id = 1,
//            title = "The Odyssey",
//            authors = listOf(Author("Homer", -800, -701)),
//            coverUrl = null,
//            subjects = listOf("Epic poetry")
//        )
//
//        var clickedId: Int? = null
//
//        composeTestRule.setContent {
//            BookCard(book = book, onClick = { clickedId = it })
//        }
//
//        composeTestRule.onNodeWithText("The Odyssey").assertExists()
//        composeTestRule.onNodeWithText("Homer").assertExists()
//
//        composeTestRule.onNodeWithText("The Odyssey").performClick()
//
//        assert(clickedId == 1)
//    }
//
//    @Test
//    fun bookDetails_displaysAuthorAndSubject() {
//        val book = Book(
//            id = 2,
//            title = "Iliad",
//            authors = listOf(Author("Homer", -800, -701)),
//            coverUrl = null,
//            subjects = listOf("War")
//        )
//
//        composeTestRule.setContent {
//            Column {
//                Text(book.title)
//                Text("Author: ${book.authors.first().name}")
//                Text("Birth: ${book.authors.first().birthYear}")
//                Text("Death: ${book.authors.first().deathYear}")
//                Text("Subject: ${book.subjects?.first()}")
//            }
//        }
//
//        composeTestRule.onNodeWithText("Iliad").assertExists()
//        composeTestRule.onNodeWithText("Author: Homer").assertExists()
//        composeTestRule.onNodeWithText("Birth: -800").assertExists()
//        composeTestRule.onNodeWithText("Death: -701").assertExists()
//        composeTestRule.onNodeWithText("Subject: War").assertExists()
//    }
//}