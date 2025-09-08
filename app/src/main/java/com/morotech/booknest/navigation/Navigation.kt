package com.morotech.booknest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    data object List : Screen("books")
    data object Details : Screen("details/{id}") {
        fun route(id: Int) = "details/$id"
    }
}


class NavActions(private val navigator: ScreenNavigator) {
    fun goToList() = navigator.navigate(Screen.List)
    fun goToDetails(id: Int) = navigator.navigate(Screen.Details.route(id))
    fun pop() = navigator.pop()
}

class ScreenNavigator(private val navController: NavController) {
    fun navigate(screen: Screen) = navController.navigate(screen.route)
    fun navigate(route: String) = navController.navigate(route)
    fun pop() = navController.popBackStack()
}

fun NavGraphBuilder.addScreen(
    screen: Screen,
    content: @Composable (NavBackStackEntry) -> Unit
    ) = composable(screen.route) { content(it) }

fun NavGraphBuilder.addScreenWithArgs(
     screen: Screen,
       arguments: List<androidx.navigation.NamedNavArgument>,
       content: @Composable (NavBackStackEntry) -> Unit
    ) = composable(screen.route, arguments = arguments) { content(it) }