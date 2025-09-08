package com.morotech.booknest.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.morotech.booknest.viewModel.bookDetails.BookDetailsViewModel
import com.morotech.booknest.viewModel.bookDetails.Error
import com.morotech.booknest.viewModel.bookDetails.Loaded
import com.morotech.booknest.viewModel.bookDetails.Loading
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookDetailsScreen(
    bookId: Int,
    onBack: () -> Unit,
    vm: BookDetailsViewModel = koinViewModel()
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(bookId) { vm.load(bookId) }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(com.morotech.resources.R.string.book_details)) }, navigationIcon = {
            IconButton(onClick = onBack) { Image(
                painter = painterResource(id = com.morotech.resources.R.drawable.ic_arrow_back),
                contentDescription = "Book icon",
                modifier = Modifier.size(48.dp)
            ) }
        })
    }) { padding ->
        when (val s = state) {
            is Loading -> Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            is Error -> Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text(s.message, color = MaterialTheme.colors.error)
            }
            is Loaded -> Column(Modifier.fillMaxWidth().padding(16.dp).padding(padding)) {
                s.book.coverUrl?.let {
                    AsyncImage(model = it, contentDescription = s.book.title, modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp))
                    Spacer(Modifier.height(12.dp))
                }
                Text(s.book.title, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
                val author = s.book.authors.firstOrNull()
                Text(stringResource(com.morotech.resources.R.string.author_label, author?.name ?: ""))
                Text(stringResource(com.morotech.resources.R.string.birth_label, author?.birthYear?.toString() ?: ""))
                Text(stringResource(com.morotech.resources.R.string.death_label, author?.deathYear?.toString() ?: ""))
                Text(stringResource(com.morotech.resources.R.string.subject_label, s.book.subjects?.firstOrNull() ?: ""))
            }
        }
    }
}