package com.reecekidd.streakr

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_select_friends_for_group_streak.*

class SelectFriendsForGroupStreakActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_friends_for_group_streak)

        val streakName:String = intent.getStringExtra(CreateGroupStreak.GROUP_STREAK_NAME_KEY)
        val streakDescription: String = intent.getStringExtra(CreateGroupStreak.GROUP_STREAK_DESCRIPTION_KEY)

        selectFriendsForGroupStreakNextButton.setOnClickListener {
            val intent = Intent(this.applicationContext, SoloStreakCreatedActivity::class.java)
            intent.putExtra(CreateGroupStreak.GROUP_STREAK_NAME_KEY, streakName)
            intent.putExtra(CreateGroupStreak.GROUP_STREAK_DESCRIPTION_KEY, streakDescription)
            this.applicationContext.startActivity(intent)
        }
    }

}
