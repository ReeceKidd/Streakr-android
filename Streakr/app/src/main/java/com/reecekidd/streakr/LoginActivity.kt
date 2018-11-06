package com.reecekidd.streakr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerTextView.setOnClickListener {
            val intent = Intent(this.applicationContext, RegisterActivity::class.java)
            this.applicationContext.startActivity(intent)
        }

        loginButton.setOnClickListener {
            val intent = Intent(this.applicationContext, UserFeedActivity::class.java)
            this.applicationContext.startActivity(intent)
        }

    }

}
