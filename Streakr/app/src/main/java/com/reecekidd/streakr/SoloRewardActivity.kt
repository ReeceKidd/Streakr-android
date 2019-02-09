package com.reecekidd.streakr

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import kotlinx.android.synthetic.main.activity_solo_forfeit.*

import kotlinx.android.synthetic.main.activity_solo_reward.*

class SoloRewardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solo_reward)
        soloRewardSkipButton.setOnClickListener {
            val streakCreatedIntent = Intent(this.applicationContext, StreakCreatedActivity::class.java)
            this.applicationContext.startActivity(streakCreatedIntent)
        }

        soloRewardSubmitButton.setOnClickListener {
            val streakCreatedIntent = Intent(this.applicationContext, StreakCreatedActivity::class.java)
            this.applicationContext.startActivity(streakCreatedIntent)
        }
    }

}
