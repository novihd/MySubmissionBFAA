package com.example.mysubmission2.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysubmission2.R
import com.example.mysubmission2.api.ClientRetrofit
import com.example.mysubmission2.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    val listFollow = MutableLiveData<ArrayList<UserModel>>()

    fun setListFollowing(username: String, context: Context) {
        ClientRetrofit.apiInstance
                .getFollowing(username)
                .enqueue(object : Callback<ArrayList<UserModel>>{
                    override fun onResponse(call: Call<ArrayList<UserModel>>, response: Response<ArrayList<UserModel>>) {
                        if (response.isSuccessful && response.body() != null) {
                            val gitFollowing = response.body()
                            if (!gitFollowing?.isEmpty()!!) {
                                listFollow.postValue(response.body())
                            }
                            else {
                                listFollow.postValue(response.body())
                                Toast.makeText(context, R.string.following_null, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                        t.message?.let { Log.d("Failure", it) }
                    }

                })
    }

    fun getListFollowing(): LiveData<ArrayList<UserModel>> {
        return listFollow
    }
}