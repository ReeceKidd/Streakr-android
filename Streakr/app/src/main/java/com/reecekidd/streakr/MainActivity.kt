package com.reecekidd.streakr

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon

import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences(
                getString(R.string.shared_preferences_api_key), Context.MODE_PRIVATE)
        val jsonWebToken = sharedPreferences.getString(getString(R.string.json_web_token), null)
        if(jsonWebToken != null && jsonWebToken.isNotEmpty()){
            Log.d(tag, "jsonWebToken is not empty")
            val homeAcvitityIntent = Intent(this.applicationContext, HomeActivity::class.java)
            this.applicationContext.startActivity(homeAcvitityIntent)
            return
        }

        Log.d(tag, "startingRegisterActivity")
        val registerAcvitityIntent = Intent(this.applicationContext, RegisterActivity::class.java)
        this.applicationContext.startActivity(registerAcvitityIntent)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onstart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart")
    }

}
