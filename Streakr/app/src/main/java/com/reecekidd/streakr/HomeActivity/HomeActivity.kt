package com.reecekidd.streakr.HomeActivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.reecekidd.streakr.CreateGroupStreak.CreateGroupStreak
import com.reecekidd.streakr.CreateSoloStreak.CreateSoloStreak
import com.reecekidd.streakr.ManageFriends.FriendsActivity
import com.reecekidd.streakr.R

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

        friendsButton.setOnClickListener {
            val intent = Intent(this.applicationContext, FriendsActivity::class.java)
            this.applicationContext.startActivity(intent)
        }

    }

}
