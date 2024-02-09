package com.example.hw2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.livedata.observeAsState


// Define the MyApp composable, including the `NavController` and `NavHost`.
    @Composable
    fun Navigation(
    ) {
        val navController = rememberNavController()
        val userViewModel: UserViewModel = viewModel()
 //       val user by userViewModel.user.observeAsState()
    NavHost(
        navController = navController,
        startDestination = "preview"
    ) {
        composable("preview") {
            Conversation(
                SampleData.conversationSample,
                navController = navController,
                userViewModel
            )
        }
        composable("second") {
            SecondScreen(
                navController = navController,
                userViewModel
            )
        }
    }
}



