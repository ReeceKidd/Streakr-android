package com.reecekidd.streakr.Friends

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.reecekidd.streakr.AddFriend.AddFriendActivity
import com.reecekidd.streakr.CreateSoloStreak.CreateSoloStreakActivity
import com.reecekidd.streakr.R

import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        setSupportActionBar(toolbar)

        fab_add_friend.setOnClickListener { view ->
            val intent = Intent(this.applicationContext, AddFriendActivity::class.java)
            this.applicationContext.startActivity(intent)
        }
    }

}
