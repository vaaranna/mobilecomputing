package com.example.hw2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val database: MessageDatabase by lazy {
        Room.databaseBuilder(
            application,
            MessageDatabase::class.java, "message-database"
        ).fallbackToDestructiveMigration()
            .build()
    }
    init {
        viewModelScope.launch {
            if (database.messageDao().getAllSync().isEmpty()) {
                database.messageDao().insertAll(*SampleData.conversationSample.toTypedArray())
            }
        }
    }
    val messages: LiveData<List<Message>> = database.messageDao().getAll()
    fun addMessage(author: String, body: String ) {
        val currentMessage = Message(author = author, body = body)
        viewModelScope.launch {
            database.messageDao().insertAll(currentMessage)
        }
    }
    fun updateMessage(message: Message) {
        viewModelScope.launch {
            database.messageDao().update(message)
        }
    }
}