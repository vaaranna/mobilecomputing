package com.example.hw2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Message::class], version = 1, exportSchema = false )
abstract class MessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}