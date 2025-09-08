package com.morotech.booknest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.morotech.booknest.ui.BookDetailsScreen
import com.morotech.booknest.ui.BookListScreen

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.List.route) {
        val navActions = NavActions(ScreenNavigator(navController))
        addScreenWithArgs(
            screen = Screen.Details,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("id") ?: return@addScreenWithArgs
            BookDetailsScreen(bookId = id, onBack = navActions::pop)
        }

        addScreen(Screen.List) {
            // expects a (Int) -> Unit
            BookListScreen(onBookClick = navActions::goToDetails)
        }
    }
}