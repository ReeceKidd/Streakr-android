package com.reecekidd.streakr

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import kotlinx.android.synthetic.main.activity_create_solo_streak.*

import kotlinx.android.synthetic.main.activity_solo_streak_created.*

class SoloStreakCreatedActivity : AppCompatActivity() {

    var LOG_TAG = "SoloStreakCreatedActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solo_streak_created)
        val streakName:String = intent.getStringExtra(CreateSoloStreak.SOLO_STREAK_NAME_KEY)
        val streakDescription: String = intent.getStringExtra(CreateSoloStreak.SOLO_STREAK_DESCRIPTION_KEY)
        createdSoloStreakName.text = streakName
        createdSoloStreakDescription.text = streakDescription

        soloStreakCreatedHomeButton.setOnClickListener {
            val intent = Intent(this.applicationContext, HomeActivity::class.java)
            this.applicationContext.startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
    }


}
