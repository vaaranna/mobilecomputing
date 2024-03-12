package com.example.hw2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {
    
    val user = MutableLiveData<User?>()

    val db: UserDatabase by lazy {
        Room.databaseBuilder(application, UserDatabase::class.java, "userprofile")
            .fallbackToDestructiveMigration()
            .build()
    }
    init {
        loadUser()
    }

    fun saveUserProfile(username: String, imageUri: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val newUser = User(username = username, image = imageUri ?: "")
            db.userDao().insertAndUpdateUser(newUser)
            user.postValue(newUser)
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            user.postValue(db.userDao().getUser())
        }
    }
}
       
       