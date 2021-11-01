package com.example.mysubmission2.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserFavDao {
    @Insert
    suspend fun addToFavorite(userFavorite: UserFav)

    @Query("SELECT * FROM user_favorite")
    fun getUserFav(): LiveData<List<UserFav>>

    @Query("SELECT count(*) FROM user_favorite WHERE user_favorite.id = :id")
    suspend fun checkUser(id: Int): Int

    @Query("DELETE FROM user_favorite WHERE user_favorite.id = :id")
    suspend fun removeFromFavorite(id: Int): Int

    @Query("SELECT * FROM user_favorite")
    fun findAll(): Cursor
}