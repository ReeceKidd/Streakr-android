package com.reecekidd.streakr

import android.util.Log

val UserTag = "User"

class User(val firstName: String, val lastName: String, val userName: String, val email: String, val password: String) {

    init {
        Log.d(UserTag, "User constructor called")
    }


}