package com.reecekidd.streakr

import com.reecekidd.streakr.User.UserData
import java.util.*

class CreateStreakServerResponse(
        var startDate: Date,
        var calendar: Array<kotlin.Any>,
        var _id: String,
        var streakName: String,
        var streakDescription: String,
        var user: UserData,
        var createdAt: String,
        var updatedAt: String
)