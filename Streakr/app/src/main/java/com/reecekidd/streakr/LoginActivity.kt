package com.reecekidd.streakr

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val tag = "Login activity"

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
