package com.example.hw2
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAndUpdateUser(users: User): Long

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM User ORDER BY uid DESC LIMIT 1")
    suspend fun getUser(): User?
}