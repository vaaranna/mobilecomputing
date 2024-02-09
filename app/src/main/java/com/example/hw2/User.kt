package com.example.hw2

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val username: String,
    val image: String
)