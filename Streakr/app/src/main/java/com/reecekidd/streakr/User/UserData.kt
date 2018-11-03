package com.reecekidd.streakr.User

import com.reecekidd.streakr.Streak

class UserData(
        val firstName: String,
        val lastName: String,
        val userName: String,
        val email: String,
        val streaks: Array<Streak>,
        val profilePicture: String
) {
    fun getFullName(): String {
    return "${this.firstName} ${this.lastName}"
    }
}

