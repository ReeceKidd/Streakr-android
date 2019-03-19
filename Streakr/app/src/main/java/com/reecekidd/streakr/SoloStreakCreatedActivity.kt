package com.reecekidd.streakr

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_solo_streak_created.*

class SoloStreakCreatedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solo_streak_created)
        val streakName:String = intent.getStringExtra(CreateSoloStreak.SoloStreak.STREAK_NAME)
        val streakDescription: String = intent.getStringExtra(CreateSoloStreak.SoloStreak.STREAK_DESCRIPTION)
        createdSoloStreakName.setText(streakName)
        createdSoloStreakDescription.setText(streakDescription)
    }

}
