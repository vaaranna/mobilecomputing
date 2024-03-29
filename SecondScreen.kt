package com.example.hw2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController

// second screenin alustus
    @Composable
    fun SecondScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("List of my friends")
        Button(
            onClick = { navController.popBackStack() }
        ) {
            Text("back to the messages")
        }
    }
}