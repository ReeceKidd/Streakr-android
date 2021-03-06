package com.reecekidd.streakr.SoloStreaks

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.reecekidd.streakr.CreateSoloStreak.CreateSoloStreakActivity
import com.reecekidd.streakr.R

import kotlinx.android.synthetic.main.activity_solo_streaks.*

class SoloStreaksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solo_streaks)
        setSupportActionBar(toolbar)

        fab_create_solo_streak.setOnClickListener { view ->
            val intent = Intent(this.applicationContext, CreateSoloStreakActivity::class.java)
            this.applicationContext.startActivity(intent)
        }
    }

}
