package com.example.consumerapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var list = MutableLiveData<ArrayList<UserModel>>()

    fun setUserFavorite(context: Context) {
        val cursor = context.contentResolver.query(
            DatabaseContract.UserFavoriteColumns.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val listConverted = MappingHelper.mapCursorToArrayList(cursor)
        list.postValue(listConverted)
    }

    fun getUserFav(): LiveData<ArrayList<UserModel>> {
        return list
    }
}