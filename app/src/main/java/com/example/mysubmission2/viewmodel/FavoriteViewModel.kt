package com.example.mysubmission2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mysubmission2.local.UserDatabase
import com.example.mysubmission2.local.UserFav
import com.example.mysubmission2.local.UserFavDao

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDao : UserFavDao?
    private var userDb: UserDatabase? = UserDatabase.getDatabase(application)

    init {
        userDao = userDb?.userFavDao()
    }

    fun getUserFav(): LiveData<List<UserFav>>? {
        return userDao?.getUserFav()
    }
}