package com.example.hw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.hw2.ui.theme.HW2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // createNotificationChannel()
        setContent {
            HW2Theme {
                Navigation()
            }
        }
    }
}




