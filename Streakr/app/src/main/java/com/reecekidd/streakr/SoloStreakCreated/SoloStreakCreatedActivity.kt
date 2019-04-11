package com.reecekidd.streakr.SoloStreakCreated

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.reecekidd.streakr.CreateSoloStreak.CreateSoloStreakActivity
import com.reecekidd.streakr.HomeActivity.HomeActivity
import com.reecekidd.streakr.R

import kotlinx.android.synthetic.main.activity_solo_streak_created.*

class SoloStreakCreatedActivity : AppCompatActivity() {

    var LOG_TAG = "SoloStreakCreatedActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solo_streak_created)
        val streakName:String = intent.getStringExtra(CreateSoloStreakActivity.SOLO_STREAK_NAME_KEY)
        val streakDescription: String = intent.getStringExtra(CreateSoloStreakActivity.SOLO_STREAK_DESCRIPTION_KEY)
        createdSoloStreakName.text = streakName
        createdSoloStreakDescription.text = streakDescription

        soloStreakCreatedHomeButton.setOnClickListener {
            val intent = Intent(this.applicationContext, HomeActivity::class.java)
            this.applicationContext.startActivity(intent)
        }
    }

}
