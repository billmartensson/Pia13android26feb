package se.magictechnology.pia13android26feb

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TodoNav(todoviewmodel : TodoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "todolist") {
        composable("todolist") {
            TodoList(todoviewmodel)
        }
    }

}