package com.reecekidd.streakr

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.reecekidd.streakr.R
import kotlinx.android.synthetic.main.activity_register.*

import kotlinx.android.synthetic.main.activity_solo_forfeit.*

class SoloForfeitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solo_forfeit)

        soloForfeitSkipButton.setOnClickListener {
            val soloRewardActivityIntent = Intent(this.applicationContext, SoloRewardActivity::class.java)
            this.applicationContext.startActivity(soloRewardActivityIntent)
        }

        soloForfeitSubmitButton.setOnClickListener {
            val soloRewardActivityIntent = Intent(this.applicationContext, SoloRewardActivity::class.java)
            this.applicationContext.startActivity(soloRewardActivityIntent)
        }
    }

}
