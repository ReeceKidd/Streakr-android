package com.reecekidd.streakr.CreateGroupStreak

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.reecekidd.streakr.R

import kotlinx.android.synthetic.main.activity_create_group_streak.*

class CreateGroupStreak : AppCompatActivity() {

    companion object{
        const val GROUP_STREAK_NAME_KEY = "groupSteakName"
        const val GROUP_STREAK_DESCRIPTION_KEY = "groupStreakDescription"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group_streak)


        createGroupStreakNextButton.setOnClickListener {
            val intent = Intent(this.applicationContext, SelectFriendsForGroupStreakActivity::class.java)
            intent.putExtra(GROUP_STREAK_NAME_KEY, groupStreakName.text.toString())
            intent.putExtra(GROUP_STREAK_DESCRIPTION_KEY, groupStreakDescription.text.toString())
            this.applicationContext.startActivity(intent)
        }
    }

}
