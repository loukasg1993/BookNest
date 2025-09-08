package com.morotech.booknest.ui

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.morotech.booknest.viewModel.booksList.BookListAction
import com.morotech.booknest.viewModel.booksList.BookListEvent
import com.morotech.booknest.viewModel.booksList.BookListViewModel
import com.morotech.booknest.viewModel.booksList.Error
import com.morotech.booknest.viewModel.booksList.Loaded
import com.morotech.booknest.viewModel.booksList.Loading
import com.morotech.domain.model.Book
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookListScreen(
    onBookClick: (Int) -> Unit,
    vm: BookListViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by vm.state.collectAsState()

    LaunchedEffect(vm.action) {
        vm.action.collectLatest { action ->
            when (action) {
                is BookListAction.NavigateToDetails -> onBookClick(action.id)
                is BookListAction.ShowToast ->
                    Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    when (val st = state) {
        is Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        is Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(st.message ?: "", color = Color.Red)
        }

        is Loaded -> LazyColumn(Modifier.fillMaxSize()) {
            items(st.books) { book ->
                BookCard(book) {
                    vm.onEvent(BookListEvent.ItemClicked(book))
                }
            }
        }
    }
}

@Composable
fun BookCard(book: Book, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(1.dp, Color.LightGray)
            .clickable { onClick() },
        elevation = 2.dp
    ) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                if (book.coverUrl != null) {
                    AsyncImage(
                        model = book.coverUrl,
                        contentDescription = book.title,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Text("No cover", style = MaterialTheme.typography.caption)
                }
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(book.title, fontWeight = FontWeight.Bold)
                val authorLine = if (book.authors.isNotEmpty()) {
                    book.authors.firstOrNull()?.name
                } else stringResource(id = com.morotech.resources.R.string.unknown_author)
                if (authorLine != null) {
                    Text(authorLine, style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}