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
    @Headers("Authorization: token c058d33d6a02b8cab9e8cafeb6f65a44839d623f")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token c058d33d6a02b8cab9e8cafeb6f65a44839d623f")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token c058d33d6a02b8cab9e8cafeb6f65a44839d623f")
    fun getFollowers(
        @Path("username") username : String
    ): Call<ArrayList<UserModel>>

    @GET("users/{username}/following")
    @Headers("Authorization: token c058d33d6a02b8cab9e8cafeb6f65a44839d623f")
    fun getFollowing(
        @Path("username") username : String
    ): Call<ArrayList<UserModel>>



}