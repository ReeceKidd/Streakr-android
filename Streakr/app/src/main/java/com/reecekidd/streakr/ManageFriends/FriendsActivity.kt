package com.reecekidd.streakr.ManageFriends

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.reecekidd.streakr.AddFriend.AddFriendActivity
import com.reecekidd.streakr.R

class FriendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        setSupportActionBar(findViewById(R.id.friendsToolbar))
        fab.setOnClickListener { view ->
            // Redirect to the Add Friend View
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.friends, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId){
        R.id.addFriend -> {
            val intent = Intent(this.applicationContext, AddFriendActivity::class.java)
            this.applicationContext.startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}
