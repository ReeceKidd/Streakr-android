package com.reecekidd.streakr

class UserAPIResponse(
        val children: List<UserData>
)

class UserData(
        val firstName: String,
        val lastName: String,
        val userName: String,
        val email: String,
        val name: String,
        val streaks: Array<Streak>,
        val profilePicture: String
)