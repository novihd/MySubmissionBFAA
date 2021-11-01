package com.example.mysubmission2.api

import com.example.mysubmission2.model.DetailUserResponse
import com.example.mysubmission2.model.UserModel
import com.example.mysubmission2.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username : String
    ): Call<ArrayList<UserModel>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username : String
    ): Call<ArrayList<UserModel>>



}