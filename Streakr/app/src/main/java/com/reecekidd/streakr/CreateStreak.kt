package com.reecekidd.streakr

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_create_streak.*

class CreateStreak : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_streak)

        var justMe: String = getString(R.string.just_me)
        var friends: String = getString(R.string.friends)

        val whoIsInvolvedOptions = arrayOf(justMe, friends)

        whoIsInvolvedSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, whoIsInvolvedOptions)

        whoIsInvolvedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // If nothing is selected just set the default to just me. 
            }
        }
    }

}
