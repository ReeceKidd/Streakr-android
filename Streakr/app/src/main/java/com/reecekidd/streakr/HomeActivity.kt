package com.reecekidd.streakr

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        createSoloStreakButton.setOnClickListener {
            val intent = Intent(this.applicationContext, CreateSoloStreak::class.java)
            this.applicationContext.startActivity(intent)
        }

        createGroupStreakButton.setOnClickListener {
            val intent = Intent(this.applicationContext, CreateGroupStreak::class.java)
            this.applicationContext.startActivity(intent)
        }

        manageStreaksButton.setOnClickListener {
            val intent = Intent(this.applicationContext, ManageStreaks::class.java)
            this.applicationContext.startActivity(intent)
        }


    }

}
