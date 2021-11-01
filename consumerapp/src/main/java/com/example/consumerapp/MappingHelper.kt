package com.example.consumerapp

import android.database.Cursor

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<UserModel> {
        val list = ArrayList<UserModel>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.UserFavoriteColumns.ID))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserFavoriteColumns.USERNAME))
                val avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserFavoriteColumns.AVATAR_URL))
                list.add(
                    UserModel(
                        username,
                        id,
                        avatarUrl
                    )
                )
            }
        }
        return list
    }
}