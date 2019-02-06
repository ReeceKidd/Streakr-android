package com.reecekidd.streakr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_create_streak.*

class CreateStreak : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_streak)

        val justMe: String = getString(R.string.just_me)
        val friends: String = getString(R.string.friends)
        val whoIsInvolvedOptions = arrayOf(justMe, friends)
        whoIsInvolvedSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, whoIsInvolvedOptions)
        var selectedWhoIsInvolved = justMe

        whoIsInvolvedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
              selectedWhoIsInvolved = whoIsInvolvedOptions.get(position)
            }
        }

        createStreakNextButton.setOnClickListener {
            if(selectedWhoIsInvolved == friends) {
                val intent = Intent(this.applicationContext, FriendStreakRewardActivity::class.java)
                this.applicationContext.startActivity(intent)
            }

            val intent = Intent(this.applicationContext, SoloForfeitActivity::class.java)
            this.applicationContext.startActivity(intent)
        }


        // Do the network call here if the fields aren't empty
    }

}
