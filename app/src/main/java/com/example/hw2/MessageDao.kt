package com.example.hw2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MessageDao {
    @Query("SELECT * FROM Message")
    fun getAll(): LiveData<List<Message>>

    @Query("SELECT * FROM message")
    suspend fun getAllSync(): List<Message>


    @Insert
    suspend fun insertAll(vararg messages: Message)

    @Update
    fun update(message: Message)
}