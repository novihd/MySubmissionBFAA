package com.example.mysubmission2.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysubmission2.R
import com.example.mysubmission2.api.ClientRetrofit
import com.example.mysubmission2.model.UserModel
import com.example.mysubmission2.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listUser = MutableLiveData<ArrayList<UserModel>>()

    fun setSearchUser(username: String, context: Context) {

        ClientRetrofit.apiInstance
            .getSearchUser(username)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val gitUser = response.body()?.items
                    if (response.isSuccessful && response.body()?.items != null) {
                        if (!gitUser?.isEmpty()!!) {
                            listUser.postValue(response.body()?.items)
                        } else {
                            listUser.postValue(response.body()?.items)
                            Toast.makeText(context, R.string._404, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(context, R.string.network_failure, Toast.LENGTH_SHORT).show()
                }
            })

    }

    fun getSearchUsers() : LiveData<ArrayList<UserModel>>{
        return listUser
    }



}