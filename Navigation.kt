package com.example.hw2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


// Define the MyApp composable, including the `NavController` and `NavHost`.
    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "preview"
        ) {
            composable("preview") { PreviewConversation(navController = navController) }
            composable("second") { SecondScreen(navController = navController)}
        }
}





