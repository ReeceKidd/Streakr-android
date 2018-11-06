package com.reecekidd.streakr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val tag = "Login activity"

        loginTextView.setOnClickListener {
            val intent = Intent(this.applicationContext, LoginActivity::class.java)
            this.applicationContext.startActivity(intent)
        }

        registerButton.setOnClickListener {
            val userNameText = userNameTextField.text.toString()
            val emailText = emailTextField.text.toString()
            val passwordText = passwordTextField.text.toString()
            Log.d(tag, "Username: $userNameText")
            Log.d(tag, "Email $emailText")
            Log.d(tag, "Password $passwordText")
        }

    }

}
