package com.example.hw2

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


// Define the MyApp composable, including the `NavController` and `NavHost`.
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    fun Navigation(
    ) {
        val navController = rememberNavController()
        val userViewModel: UserViewModel = viewModel()
        val chatViewModel: ChatViewModel = viewModel()
 //       val user by userViewModel.user.observeAsState()
    NavHost(
        navController = navController,
        startDestination = "preview"
    ) {
        composable("preview") {
            Conversation(
                //SampleData.conversationSample,
                navController = navController,
                chatViewModel = chatViewModel,
                userViewModel = userViewModel

            )
        }
        composable("second") {
            SecondScreen(
                navController = navController,
                userViewModel
            )
        }
        composable("camera") {
            val context = LocalContext.current
            val activity = remember { mutableStateOf<Activity?>(null) }

            LaunchedEffect(context) {
                activity.value = context.findActivity()
            }

            activity.value?.let {
                Camera(
                    context = context,
                    navController = navController,
                    activity = it
                )
            }
        }
    }
}



