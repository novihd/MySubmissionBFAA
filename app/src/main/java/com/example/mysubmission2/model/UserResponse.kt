package com.example.mysubmission2.model

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("items")
    val items : ArrayList<UserModel>
        )